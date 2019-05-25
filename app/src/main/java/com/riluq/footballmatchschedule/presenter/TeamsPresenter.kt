package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.CoroutineContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.api.TheSportDBApi
import com.riluq.footballmatchschedule.model.TeamResponse
import com.riluq.footballmatchschedule.utils.NO_PARAMETER
import com.riluq.footballmatchschedule.view.TeamsView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(league: String?, teamSearching: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {

            if (teamSearching == NO_PARAMETER) {
                val data = gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getTeams(league)).await(),
                            TeamResponse::class.java
                    )

                view.showTeamList(data.teams)
                view.hideLoading()
            } else if (league == NO_PARAMETER) {
                val dataSearch = gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getSearchTeam(teamSearching)).await(),
                            TeamResponse::class.java
                    )

                view.showTeamList(dataSearch.teams)
                view.hideLoading()
            }

        }
    }

}