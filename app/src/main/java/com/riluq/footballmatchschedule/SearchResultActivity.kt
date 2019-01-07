package com.riluq.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.riluq.footballmatchschedule.adapter.MatchAdapter
import com.riluq.footballmatchschedule.adapter.TeamsAdapter
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.model.PrevMatch
import com.riluq.footballmatchschedule.model.Team
import com.riluq.footballmatchschedule.presenter.NextMatchPresenter
import com.riluq.footballmatchschedule.presenter.PrevMatchPresenter
import com.riluq.footballmatchschedule.presenter.TeamsPresenter
import com.riluq.footballmatchschedule.utils.*
import com.riluq.footballmatchschedule.view.PrevMatchView
import com.riluq.footballmatchschedule.view.TeamsView
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_search_result.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class SearchResultActivity : AppCompatActivity(), PrevMatchView, TeamsView {
    private var searchId: String = ""

    private var searchQuery: String = ""
    private lateinit var prevPresenter: PrevMatchPresenter
    private lateinit var matchAdapter: MatchAdapter

    private lateinit var teamPresenter: TeamsPresenter
    private lateinit var teamAdapter: TeamsAdapter
    private var prevMatchs: MutableList<PrevMatch> = mutableListOf()

    private var teams: MutableList<Team> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        searchId = intent.getStringExtra(SEARCH_ID)
        searchQuery = intent.getStringExtra(INTENT_SEARCH)

        rv_search.layoutManager = LinearLayoutManager(applicationContext)

        if (searchId == SEARCH_MATCH_ID) {
            matchAdapter = MatchAdapter(applicationContext, prevMatchs){
                startActivity<MatchDetailActivity>(
                        "idEvent" to "${it.idEvent}",
                        "teamIdHome" to "${it.teamIdHome}",
                        "teamIdAway" to "${it.teamIdAway}")
            }
            rv_search.adapter = matchAdapter
        } else if (searchId == SEARCH_TEAM_ID) {
            teamAdapter = TeamsAdapter(teams) {
                ctx.startActivity<TeamDetail2Activity>("id" to "${it.teamId}")
            }
            rv_search.adapter = teamAdapter
        }

        val request = ApiRepository()
        val gson = Gson()
        prevPresenter = PrevMatchPresenter(this, request, gson)
        teamPresenter = TeamsPresenter(this, request, gson)

        if (searchId == SEARCH_MATCH_ID) {
            prevPresenter.getPrevMatchList(NO_PARAMETER, searchQuery)
        } else if (searchId == SEARCH_TEAM_ID) {
            teamPresenter.getTeamList(NO_PARAMETER, searchQuery)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

    override fun showLoading() {
        pb_search.visible()
    }

    override fun hideLoading() {
        pb_search.invisible()
    }

    override fun showPrevMatchList(data: List<PrevMatch>) {
        prevMatchs.clear()
        prevMatchs.addAll(data)
        matchAdapter.notifyDataSetChanged()
    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        teamAdapter.notifyDataSetChanged()
    }
}
