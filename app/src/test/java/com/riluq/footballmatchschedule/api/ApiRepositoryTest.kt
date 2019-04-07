package com.riluq.footballmatchschedule.api

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Mock
    val theSportDBApi = TheSportDBApi

    @Test
    fun doRequest() {
        val leagueId = "4328"
        val apiRepository = mock(ApiRepository::class.java)

        val urlPrevMatch = theSportDBApi.getPrevMatch(leagueId)
        apiRepository.doRequest(urlPrevMatch)
        verify(apiRepository).doRequest(urlPrevMatch)

        val urlNextMath = theSportDBApi.getNextMatch(leagueId)
        apiRepository.doRequest(urlNextMath)
        verify(apiRepository).doRequest(urlNextMath)

        val urlEventById = theSportDBApi.getEventById("441613")
        apiRepository.doRequest(urlEventById)
        verify(apiRepository).doRequest(urlEventById)

        val urlTeamDetails = theSportDBApi.getTeamDetails("133604")
        apiRepository.doRequest(urlTeamDetails)
        verify(apiRepository).doRequest(urlTeamDetails)


    }
}