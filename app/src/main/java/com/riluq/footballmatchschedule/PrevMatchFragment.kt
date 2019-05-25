package com.riluq.footballmatchschedule


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.riluq.footballmatchschedule.R.color.colorAccent
import com.riluq.footballmatchschedule.adapter.MatchAdapter
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.model.PrevMatch
import com.riluq.footballmatchschedule.presenter.PrevMatchPresenter
import com.riluq.footballmatchschedule.utils.NO_PARAMETER
import com.riluq.footballmatchschedule.utils.invisible
import com.riluq.footballmatchschedule.utils.visible
import com.riluq.footballmatchschedule.view.PrevMatchView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout


/**
 * A simple [Fragment] subclass.
 *
 */
class PrevMatchFragment : Fragment(), AnkoComponent<Context>, PrevMatchView {

    private lateinit var listEvent: RecyclerView

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var prevMatchs: MutableList<PrevMatch> = mutableListOf()

    private lateinit var spinner: Spinner

    private lateinit var prevPresenter: PrevMatchPresenter
    private lateinit var adapter: MatchAdapter

    private lateinit var leagueId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = MatchAdapter(requireActivity(), prevMatchs){
            startActivity<MatchDetailActivity>(
                    "idEvent" to "${it.idEvent}",
                    "teamIdHome" to "${it.teamIdHome}",
                    "teamIdAway" to "${it.teamIdAway}")
        }
        listEvent.adapter = adapter


        val request = ApiRepository()
        val gson = Gson()
        prevPresenter = PrevMatchPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val leagueIdItemSelected = resources.getStringArray(R.array.league_id)
                when (position) {
                    0 -> leagueId = leagueIdItemSelected[0]
                    1 -> leagueId = leagueIdItemSelected[1]
                    2 -> leagueId = leagueIdItemSelected[2]
                    3 -> leagueId = leagueIdItemSelected[3]
                    4 -> leagueId = leagueIdItemSelected[4]
                    5 -> leagueId = leagueIdItemSelected[5]
                }
                prevPresenter.getPrevMatchList(leagueId, NO_PARAMETER)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }



        swipeRefresh.onRefresh {
            prevPresenter.getPrevMatchList(leagueId, NO_PARAMETER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.create(ctx))
    }




    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            padding = dip(16)

            spinner = spinner {
                id = R.id.spinner
            }
            swipeRefresh = swipeRefreshLayout {
                id = R.id.srl_prev_match
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)


                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        id = R.id.rv_prev_match
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                        id = R.id.pb_prev_match
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPrevMatchList(data: List<PrevMatch>?) {
        swipeRefresh.isRefreshing = false
        prevMatchs.clear()
        if (data != null) {
            prevMatchs.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }



}
