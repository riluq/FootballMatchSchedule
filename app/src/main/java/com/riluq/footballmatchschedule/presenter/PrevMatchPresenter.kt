package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.CoroutineContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.api.TheSportDBApi
import com.riluq.footballmatchschedule.model.PrevMatchResponse
import com.riluq.footballmatchschedule.utils.NO_PARAMETER
import com.riluq.footballmatchschedule.view.PrevMatchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPrevMatchList(leagueId: String?, matchEventSearching: String?) {
        view.showLoading()
        async(context.main) {
            if (matchEventSearching == NO_PARAMETER) {
                val data = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getPrevMatch(leagueId)),
                            PrevMatchResponse::class.java
                    )
                }
                view.showPrevMatchList(data.await().events)
                view.hideLoading()
            } else if (leagueId == NO_PARAMETER) {
                val dataSearch = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getSearchMatchEvent(matchEventSearching)),
                            PrevMatchResponse::class.java
                    )
                }
                view.showPrevMatchList(dataSearch.await().event)
                view.hideLoading()
            }
        }
    }

}