package com.riluq.footballmatchschedule.presenter

import com.google.gson.Gson
import com.riluq.footballmatchschedule.TestContextProvider
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.model.PrevMatch
import com.riluq.footballmatchschedule.model.PrevMatchResponse
import com.riluq.footballmatchschedule.model.TeamDetail
import com.riluq.footballmatchschedule.model.TeamDetailResponse
import com.riluq.footballmatchschedule.view.MatchDetailView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock
    private
    lateinit var view: MatchDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamDetailList() {
        val prevMatchs: MutableList<PrevMatch> = mutableListOf()
        val responsePrevMatch = PrevMatchResponse(prevMatchs,prevMatchs)
        val idEvent = "441613"

        val teamHomeDetails: MutableList<TeamDetail> = mutableListOf()
        val responseTeamHomeDetail = TeamDetailResponse(teamHomeDetails)
        val idHomeTeam = "133602"

        val teamAwayDetails: MutableList<TeamDetail> = mutableListOf()
        val responseTeamAwayDetail = TeamDetailResponse(teamAwayDetails)
        val idAwayTeam = "133614"

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
            ).thenReturn(responsePrevMatch)

            Mockito.`when`(
                    gson.fromJson(
                            "",
                            TeamDetailResponse::class.java
                    )
            ).thenReturn(responseTeamHomeDetail)

            Mockito.`when`(
                    gson.fromJson(
                            "",
                            TeamDetailResponse::class.java
                    )
            ).thenReturn(responseTeamAwayDetail)



//            Mockito.`when`(gson.fromJson(apiRepository
//                    .doRequest(TheSportDBApi.getEventById(idEvent)),
//                    PrevMatchResponse::class.java
//            )).thenReturn(responsePrevMatch)
//
//            Mockito.`when`(gson.fromJson(apiRepository
//                    .doRequest(TheSportDBApi.getTeamDetails(idHomeTeam)),
//                    TeamDetailResponse::class.java
//            )).thenReturn(responseTeamHomeDetail)
//
//            Mockito.`when`(gson.fromJson(apiRepository
//                    .doRequest(TheSportDBApi.getTeamDetails(idAwayTeam)),
//                    TeamDetailResponse::class.java
//            )).thenReturn(responseTeamAwayDetail)

            presenter.getTeamDetailList(idEvent, idHomeTeam, idAwayTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamDetailList(prevMatchs, teamHomeDetails, teamAwayDetails)
            Mockito.verify(view).hideLoading()
        }
    }
}