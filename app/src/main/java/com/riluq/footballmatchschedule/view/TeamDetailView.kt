package com.riluq.footballmatchschedule.view

import com.riluq.footballmatchschedule.model.Players
import com.riluq.footballmatchschedule.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>, dataPlayers: List<Players>)
}