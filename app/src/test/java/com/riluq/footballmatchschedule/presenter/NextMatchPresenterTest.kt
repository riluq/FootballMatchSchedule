package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.TestContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.model.PrevMatch
import com.riluq.footballmatchschedule.model.PrevMatchResponse
import com.riluq.footballmatchschedule.view.PrevMatchView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private
    lateinit var view: PrevMatchView

    @Mock
    private
    lateinit var gson: Gson


    @Mock
    private
    lateinit var apiResponse: Deferred<String>

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getNextMatchList() {
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


//            `when`(gson.fromJson(apiRepository
//                    .doRequest(TheSportDBApi.getPrevMatch(leagueId)),
//                    PrevMatchResponse::class.java
//            )).thenReturn(response)

            presenter.getNextMatchList(leagueId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPrevMatchList(prevMatchs)
            Mockito.verify(view).hideLoading()

        }

    }
}