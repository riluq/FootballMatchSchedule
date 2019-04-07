package com.riluq.footballmatchschedule.view

import com.riluq.footballmatchschedule.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}