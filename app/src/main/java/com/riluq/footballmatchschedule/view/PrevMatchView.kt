package com.riluq.footballmatchschedule.view

import com.riluq.footballmatchschedule.model.PrevMatch

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPrevMatchList(data: List<PrevMatch>?)
}