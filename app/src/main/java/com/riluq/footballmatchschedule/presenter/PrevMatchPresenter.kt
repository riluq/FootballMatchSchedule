package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.CoroutineContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.api.TheSportDBApi
import com.riluq.footballmatchschedule.model.PrevMatchResponse
import com.riluq.footballmatchschedule.utils.NO_PARAMETER
import com.riluq.footballmatchschedule.view.PrevMatchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPrevMatchList(leagueId: String?, matchEventSearching: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            if (matchEventSearching == NO_PARAMETER) {
                val data = gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getPrevMatch(leagueId)).await(),
                            PrevMatchResponse::class.java
                    )

                view.showPrevMatchList(data.events)
                view.hideLoading()
            } else if (leagueId == NO_PARAMETER) {
                val dataSearch = gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getSearchMatchEvent(matchEventSearching)).await(),
                            PrevMatchResponse::class.java
                    )

                view.showPrevMatchList(dataSearch.event)
                view.hideLoading()
            }
        }
    }

}