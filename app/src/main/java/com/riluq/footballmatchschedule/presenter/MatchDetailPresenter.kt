package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.CoroutineContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.api.TheSportDBApi
import com.riluq.footballmatchschedule.model.PrevMatchResponse
import com.riluq.footballmatchschedule.model.TeamDetailResponse
import com.riluq.footballmatchschedule.view.MatchDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetailList(idEvent: String?, idTeam: String?, idTeam2: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val event =
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getEventById(idEvent)).await(),
                        PrevMatchResponse::class.java
                )


            val data =
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetails(idTeam)).await(),
                        TeamDetailResponse::class.java
                )

            val data2 =
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetails(idTeam2)).await(),
                        TeamDetailResponse::class.java
                )



            view.showTeamDetailList(event.events, data.teams, data2.teams)
            view.hideLoading()

        }
    }
}