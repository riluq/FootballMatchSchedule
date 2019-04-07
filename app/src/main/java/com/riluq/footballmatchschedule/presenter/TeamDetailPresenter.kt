package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.CoroutineContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.api.TheSportDBApi
import com.riluq.footballmatchschedule.model.PlayerResponse
import com.riluq.footballmatchschedule.model.TeamResponse
import com.riluq.footballmatchschedule.view.TeamDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        GlobalScope.launch(contextPool.main) {
            val data =
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetails(teamId)).await(),
                        TeamResponse::class.java
                )


            val dataPlayers =
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamPlayers(teamId)).await(),
                        PlayerResponse::class.java
                )


            view.showTeamDetail(data.teams, dataPlayers.player)
            view.hideLoading()

        }
    }

}