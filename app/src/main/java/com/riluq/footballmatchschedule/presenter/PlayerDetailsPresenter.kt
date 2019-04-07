package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.CoroutineContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.api.TheSportDBApi
import com.riluq.footballmatchschedule.model.PlayerDetailResponse
import com.riluq.footballmatchschedule.view.PlayerDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailsPresenter(private val view: PlayerDetailView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(playerId: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayerDetails(playerId)).await(),
                        PlayerDetailResponse::class.java
                )

            view.showPlayerDetailList(data.players)
            view.hideLoading()

        }
    }

}