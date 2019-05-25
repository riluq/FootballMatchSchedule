package com.riluq.footballmatchschedule.view

import com.riluq.footballmatchschedule.model.PrevMatch
import com.riluq.footballmatchschedule.model.TeamDetail

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetailList(event: List<PrevMatch>, data: List<TeamDetail>, data2: List<TeamDetail>)
}