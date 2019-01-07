package com.riluq.footballmatchschedule


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.Gson
import com.riluq.footballmatchschedule.adapter.PlayersAdapter
import com.riluq.footballmatchschedule.adapter.TeamsAdapter
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.model.Players
import com.riluq.footballmatchschedule.model.Team
import com.riluq.footballmatchschedule.presenter.TeamDetailPresenter
import com.riluq.footballmatchschedule.utils.NO_PARAMETER
import com.riluq.footballmatchschedule.view.TeamDetailView
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
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
class PlayersTeamFragment : Fragment(), AnkoComponent<Context>, TeamDetailView {
    private lateinit var listPlayer: RecyclerView

    private var players: MutableList<Players> = mutableListOf()

    private lateinit var adapter: PlayersAdapter

    private lateinit var presenter: TeamDetailPresenter

    private lateinit var idTeam: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = PlayersAdapter(requireActivity(), players) {
            ctx.startActivity<PlayerDetailsActivity>(
                    "id" to "${it.playerId}",
                    "playerName" to "${it.playerName}")
        }
        listPlayer.adapter = adapter

        val bindData = arguments
        idTeam = bindData?.getString(TeamDetail2Activity.SectionsPagerAdapter.KEY_TEAM_2) ?: "idTeam"

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(idTeam)
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
            topPadding = dip(16)
            rightPadding = dip(16)
            rightPadding = dip(16)


            listPlayer = recyclerView {
                id = R.id.list_player
                lparams(width = matchParent, height = wrapContent)
                layoutManager = LinearLayoutManager(ctx)
            }
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showTeamDetail(data: List<Team>, dataPlayers: List<Players>) {
        players.clear()
        players.addAll(dataPlayers)
        adapter.notifyDataSetChanged()
    }

}
