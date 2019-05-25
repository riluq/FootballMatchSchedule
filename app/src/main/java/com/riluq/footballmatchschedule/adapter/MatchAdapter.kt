package com.riluq.footballmatchschedule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riluq.footballmatchschedule.R
import com.riluq.footballmatchschedule.R.id.*
import com.riluq.footballmatchschedule.model.PrevMatch
import org.jetbrains.anko.find

class MatchAdapter(private val context: Context,
                       private val prevMatchs: List<PrevMatch>,
                       private val listener: (PrevMatch) -> Unit):
        RecyclerView.Adapter<PrevMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PrevMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: PrevMatchViewHolder, position: Int) {
        holder.bindItem(prevMatchs[position], listener)
    }

    override fun getItemCount(): Int = prevMatchs.size

}
class PrevMatchViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val tvHomeTeam: TextView = view.find(tv_home_team)
    private val tvAwayTeam: TextView = view.find(tv_away_team)
    private val tvHomeScore: TextView = view.find(tv_home_score)
    private val tvAwayScore: TextView = view.find(tv_away_score)
    private val tvDateEvent: TextView = view.find(tv_date_event)



    fun bindItem(prevMatchs: PrevMatch, listener: (PrevMatch) -> Unit) {
        tvHomeTeam.text = prevMatchs.teamHome
        tvAwayTeam.text = prevMatchs.teamAway
        tvHomeScore.text = prevMatchs.teamHomeScore
        tvAwayScore.text = prevMatchs.teamAwayScore
        tvDateEvent.text = prevMatchs.dateEvent
        itemView.setOnClickListener {
            listener(prevMatchs)
        }
    }
}