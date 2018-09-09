package com.riluq.footballmatchschedule

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getPrevMatchList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPrevMatch()),
                    PrevMatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showPrevMatchList(data.prevMatchs)
            }

        }
    }

}