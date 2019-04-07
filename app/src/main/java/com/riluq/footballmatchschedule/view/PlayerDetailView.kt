package com.riluq.footballmatchschedule.view

import com.riluq.footballmatchschedule.model.PlayerDetails

interface PlayerDetailView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetailList(player: List<PlayerDetails>)
}