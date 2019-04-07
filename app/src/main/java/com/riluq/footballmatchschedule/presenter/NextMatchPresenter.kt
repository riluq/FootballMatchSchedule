package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.CoroutineContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.api.TheSportDBApi
import com.riluq.footballmatchschedule.model.PrevMatchResponse
import com.riluq.footballmatchschedule.view.PrevMatchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextMatchPresenter(private val view: PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getNextMatchList(leagueId: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data =
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getNextMatch(leagueId)).await(),
                        PrevMatchResponse::class.java
                )

            view.showPrevMatchList(data.events)
            view.hideLoading()
        }
    }
}