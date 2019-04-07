package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.TestContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.model.PlayerDetailResponse
import com.riluq.footballmatchschedule.model.PlayerDetails
import com.riluq.footballmatchschedule.view.PlayerDetailView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerDetailsPresenterTest {

    @Mock
    private
    lateinit var view: PlayerDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: PlayerDetailsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerDetailsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamDetail() {
        val playerDetails: MutableList<PlayerDetails> = mutableListOf()
        val response = PlayerDetailResponse(playerDetails)
        val playerId = "34145937"

        runBlocking {

            Mockito.`when`(apiRepository
                    .doRequest(ArgumentMatchers.anyString())
            ).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                    gson.fromJson(
                            "",
                            PlayerDetailResponse::class.java
                    )
            ).thenReturn(response)

//            Mockito.`when`(gson.fromJson(apiRepository
//                    .doRequest(TheSportDBApi.getPlayerDetails(playerId)),
//                    PlayerDetailResponse::class.java
//            )).thenReturn(response)

            presenter.getTeamDetail(playerId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPlayerDetailList(playerDetails)
            Mockito.verify(view).hideLoading()
        }


    }
}