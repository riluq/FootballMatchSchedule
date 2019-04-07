package com.riluq.footballmatchschedule.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riluq.footballmatchschedule.R
import com.riluq.footballmatchschedule.database.FavoriteTeam
import com.riluq.footballmatchschedule.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class FavoriteTeamAdapter (private val favoritesTeam: List<FavoriteTeam>,
                           private val listener: (FavoriteTeam) -> Unit):
        RecyclerView.Adapter<FavoriteTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FavoriteTeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favoritesTeam[position], listener)
    }

    override fun getItemCount(): Int = favoritesTeam.size

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
        }
    }

}

class FavoriteTeamViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)

    fun bindItem(teams: Team, listener: (Team) -> Unit) {
        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName
        itemView.setOnClickListener {
            listener(teams)
        }
    }

    fun bindItem(favoritesTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Picasso.get().load(favoritesTeam.teamBadge).into(teamBadge)
        teamName.text = favoritesTeam.teamName
        itemView.setOnClickListener {
            listener(favoritesTeam)
        }
    }
}