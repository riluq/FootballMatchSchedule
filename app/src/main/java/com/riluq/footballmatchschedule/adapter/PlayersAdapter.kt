package com.riluq.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.riluq.footballmatchschedule.R
import com.riluq.footballmatchschedule.R.id.*
import com.riluq.footballmatchschedule.model.Players
import com.riluq.footballmatchschedule.model.PrevMatch
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class PlayersAdapter(private val context: Context,
                     private val players: List<Players>,
                     private val listener: (Players) -> Unit):
        RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_player, parent, false))

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.size


}

class PlayerViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val imgPlayer : ImageView = view.find(img_player_image)
    private val tvPlayerName : TextView = view.find(tv_player_name)
    private val tvPlayerPosition : TextView = view.find(tv_player_position)

    fun bindItem(players: Players, listener: (Players) -> Unit) {
        Picasso.get().load(players.playerImage).into(imgPlayer)
        tvPlayerName.text = players.playerName
        tvPlayerPosition.text = players.playerPosition
        itemView.setOnClickListener {
            listener(players)
        }
    }
}