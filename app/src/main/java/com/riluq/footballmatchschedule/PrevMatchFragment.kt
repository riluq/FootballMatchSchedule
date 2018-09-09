package com.riluq.footballmatchschedule


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import org.jetbrains.anko.*

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import com.riluq.footballmatchschedule.R.color.colorAccent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PrevMatchFragment : Fragment(), AnkoComponent<Context>, PrevMatchView {
    private lateinit var listEvent: RecyclerView

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var prevMatchs: MutableList<PrevMatch> = mutableListOf()

    private lateinit var presenter: PrevMatchPresenter
    private lateinit var adapter: PrevMatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        adapter = PrevMatchAdapter(requireContext(), prevMatchs)
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PrevMatchPresenter(this, request, gson)
        presenter.getPrevMatchList()

        // Inflate the layout for this fragment
        return createView(AnkoContext.create(ctx))
    }

    companion object {
        fun newInstance(): PrevMatchFragment = PrevMatchFragment()
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
            }

            relativeLayout {
                lparams(width = matchParent, height = wrapContent)

                listEvent = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                progressBar = progressBar {
                }.lparams{
                    centerHorizontally()
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

    override fun showPrevMatchList(data: List<PrevMatch>) {
        swipeRefresh.isRefreshing = false
        prevMatchs.clear()
        prevMatchs.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
