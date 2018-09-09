package com.riluq.footballmatchschedule

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find

import com.riluq.footballmatchschedule.R.id.tv_home_team
import com.riluq.footballmatchschedule.R.id.tv_away_team
import com.riluq.footballmatchschedule.R.id.tv_home_score
import com.riluq.footballmatchschedule.R.id.tv_away_score
import com.riluq.footballmatchschedule.R.id.tv_date_event

class PrevMatchAdapter(private val context: Context, private val prevMatchs: List<PrevMatch>): RecyclerView.Adapter<PrevMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PrevMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: PrevMatchViewHolder, position: Int) {
        holder.bindItem(prevMatchs[position])
    }

    override fun getItemCount(): Int = prevMatchs.size


}
class PrevMatchViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val tvHomeTeam: TextView = view.find(tv_home_team)
    private val tvAwayTeam: TextView = view.find(tv_away_team)
    private val tvHomeScore: TextView = view.find(tv_home_score)
    private val tvAwayScore: TextView = view.find(tv_away_score)
    private val tvDateEvent: TextView = view.find(tv_date_event)

    fun bindItem(prevMatchs: PrevMatch) {
        tvHomeTeam.text = prevMatchs.teamHome
        tvAwayTeam.text = prevMatchs.teamAway
        tvHomeScore.text = prevMatchs.teamHomeScore
        tvAwayScore.text = prevMatchs.teamAwayScore
        tvDateEvent.text = prevMatchs.dateEvent
    }

}