package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.TestContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.model.PrevMatch
import com.riluq.footballmatchschedule.model.PrevMatchResponse
import com.riluq.footballmatchschedule.utils.NO_PARAMETER
import com.riluq.footballmatchschedule.view.PrevMatchView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrevMatchPresenterTest {

    @Mock
    private
    lateinit var view: PrevMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: PrevMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getPrevMatchList() {
        val prevMatchs: MutableList<PrevMatch> = mutableListOf()
        val response = PrevMatchResponse(prevMatchs, prevMatchs)
        val leagueId = "4328"

        runBlocking {
            Mockito.`when`(apiRepository
                    .doRequest(ArgumentMatchers.anyString())
            ).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                    gson.fromJson(
                            "",
                            PrevMatchResponse::class.java
                    )
            ).thenReturn(response)

            presenter.getPrevMatchList(leagueId, NO_PARAMETER)

            verify(view).showLoading()
            verify(view).showPrevMatchList(prevMatchs)
            verify(view).hideLoading()
        }


//        `when`(gson.fromJson(apiRepository
//                .doRequest(TheSportDBApi.getPrevMatch(leagueId)),
//                PrevMatchResponse::class.java
//        )).thenReturn(response)


    }

    @Test
    fun getSearchMatchList() {
        val prevMatchs: MutableList<PrevMatch> = mutableListOf()
        val response = PrevMatchResponse(prevMatchs, prevMatchs)

        runBlocking {
            Mockito.`when`(apiRepository
                    .doRequest(ArgumentMatchers.anyString())
            ).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                    gson.fromJson(
                            "",
                            PrevMatchResponse::class.java
                    )
            ).thenReturn(response)

            presenter.getPrevMatchList(NO_PARAMETER, "Barcelona")

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPrevMatchList(prevMatchs)
            Mockito.verify(view).hideLoading()

        }

//        `when`(gson.fromJson(apiRepository
//                .doRequest(TheSportDBApi.getSearchMatchEvent("Barcelona")),
//                PrevMatchResponse::class.java
//        )).thenReturn(response)

    }
}