package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.view.TeamDetailView
import com.riluq.footballmatchschedule.model.TeamResponse
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.api.TheSportDBApi
import com.riluq.footballmatchschedule.model.PlayerResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetails(teamId)),
                        TeamResponse::class.java
                )
            }

            val dataPlayers = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamPlayers(teamId)),
                        PlayerResponse::class.java
                )
            }

            view.showTeamDetail(data.await().teams, dataPlayers.await().player)
            view.hideLoading()

        }
    }

}