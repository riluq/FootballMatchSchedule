package com.riluq.footballmatchschedule

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPrevMatchList(data: List<PrevMatch>)
}