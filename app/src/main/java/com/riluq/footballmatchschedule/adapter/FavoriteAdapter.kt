package com.riluq.footballmatchschedule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riluq.footballmatchschedule.R
import com.riluq.footballmatchschedule.database.Favorite
import org.jetbrains.anko.find

class FavoriteAdapter(private val context: Context,
                      private val favorites: List<Favorite>,
                      private val listener: (Favorite) -> Unit):
        RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorites[position], listener)
    }

    override fun getItemCount(): Int = favorites.size

}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val tvHomeTeam: TextView = view.find(R.id.tv_home_team)
    private val tvAwayTeam: TextView = view.find(R.id.tv_away_team)
    private val tvHomeScore: TextView = view.find(R.id.tv_home_score)
    private val tvAwayScore: TextView = view.find(R.id.tv_away_score)
    private val tvDateEvent: TextView = view.find(R.id.tv_date_event)



    fun bindItem(favorites: Favorite, listener: (Favorite) -> Unit) {
        tvHomeTeam.text = favorites.teamHomeName
        tvAwayTeam.text = favorites.teamAwayName
        tvHomeScore.text = favorites.teamHomeScore
        tvAwayScore.text = favorites.teamAwayScore
        tvDateEvent.text = favorites.dateEvent
        itemView.setOnClickListener {
            listener(favorites)
        }
    }
}