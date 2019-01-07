package com.riluq.footballmatchschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.riluq.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.riluq.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.riluq.footballmatchschedule.R.id.add_to_favorite
import com.riluq.footballmatchschedule.R.menu.menu_detail
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.database.Favorite
import com.riluq.footballmatchschedule.model.PrevMatch
import com.riluq.footballmatchschedule.model.TeamDetail
import com.riluq.footballmatchschedule.presenter.MatchDetailPresenter
import com.riluq.footballmatchschedule.utils.database
import com.riluq.footballmatchschedule.utils.gone
import com.riluq.footballmatchschedule.utils.visible
import com.riluq.footballmatchschedule.view.MatchDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_prev_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private var idEvent: String = ""
    private var teamIdHome: String = ""
    private var teamIdAway: String = ""

    private lateinit var presenter: MatchDetailPresenter

    private lateinit var teamDetailHome: TeamDetail
    private lateinit var teamDetailAway: TeamDetail
    private lateinit var prevMatch: PrevMatch

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_match_detail)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idEvent = intent.getStringExtra("idEvent")
        teamIdHome = intent.getStringExtra("teamIdHome")
        teamIdAway = intent.getStringExtra("teamIdAway")
        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)
        presenter.getTeamDetailList(idEvent, teamIdHome, teamIdAway)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.gone()
    }

    override fun showTeamDetailList(event: List<PrevMatch>, data: List<TeamDetail>, data2: List<TeamDetail>) {

        prevMatch = PrevMatch(event[0].dateEvent,
                event[0].teamHome,
                event[0].teamAway,
                event[0].teamHomeScore,
                event[0].teamAwayScore,
                event[0].formationHome,
                event[0].formationAway,
                event[0].teamGoalHome,
                event[0].teamGoalAway,
                event[0].teamHomeShots,
                event[0].teamAwayShots,
                event[0].teamHomeGoalkeeper,
                event[0].teamAwayGoalkeeper,
                event[0].teamHomeDefense,
                event[0].teamAwayDefense,
                event[0].teamHomeMidfield,
                event[0].teamAwayMidfield,
                event[0].teamHomeForward,
                event[0].teamAwayForward,
                event[0].teamHomeSubtitutes,
                event[0].teamAwaySubtitutes,
                event[0].idEvent)

        tv_date_event_detail.text = event[0].dateEvent
        tv_home_team.text = event[0].teamHome
        tv_away_team.text = event[0].teamAway
        tv_home_score.text = event[0].teamHomeScore
        tv_away_score.text = event[0].teamAwayScore
        tv_formation_home_team.text = event[0].formationHome
        tv_formation_away_team.text = event[0].formationAway
        tv_goals_team_home.text = replaceTheSemicolon(event[0].teamGoalHome)
        tv_goals_team_away.text = replaceTheSemicolon(event[0].teamGoalAway)
        tv_shots_team_home.text = replaceTheSemicolon(event[0].teamHomeShots)
        tv_shots_team_away.text = replaceTheSemicolon(event[0].teamAwayShots)
        tv_goalkeeper_team_home.text = replaceTheSemicolon(event[0].teamHomeGoalkeeper)
        tv_goalkeeper_team_away.text = replaceTheSemicolon(event[0].teamAwayGoalkeeper)
        tv_defense_team_home.text = replaceTheSemicolon(event[0].teamHomeDefense)
        tv_defense_team_away.text = replaceTheSemicolon(event[0].teamAwayDefense)
        tv_midfield_team_home.text = replaceTheSemicolon(event[0].teamHomeMidfield)
        tv_midfield_team_away.text = replaceTheSemicolon(event[0].teamAwayMidfield)
        tv_forward_team_home.text = replaceTheSemicolon(event[0].teamHomeForward)
        tv_forward_team_away.text = replaceTheSemicolon(event[0].teamAwayForward)
        tv_subtitutes_team_home.text = replaceTheSemicolon(event[0].teamHomeSubtitutes)
        tv_subtitutes_team_away.text = replaceTheSemicolon(event[0].teamAwaySubtitutes)

        teamDetailHome = TeamDetail(data[0].logoTeam)
        teamDetailAway = TeamDetail(data2[0].logoTeam)

        Picasso.get().load(data[0].logoTeam).into(img_logo_team_home)
        Picasso.get().load(data2[0].logoTeam).into(img_logo_team_away)
    }

    private fun replaceTheSemicolon(dataAsli: String?): String? {
        return dataAsli?.replace(";", "\n")
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to idEvent,
                        Favorite.TEAM_HOME_ID to teamIdHome,
                        Favorite.TEAM_AWAY_ID to teamIdAway,
                        Favorite.TEAM_HOME_NAME to prevMatch.teamHome,
                        Favorite.TEAM_AWAY_NAME to prevMatch.teamAway,
                        Favorite.TEAM_HOME_SCORE to prevMatch.teamHomeScore,
                        Favorite.TEAM_AWAY_SCORE to prevMatch.teamAwayScore,
                        Favorite.DATE_EVENT to prevMatch.dateEvent)
            }
            view_parent_detail.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            view_parent_detail.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to idEvent)
            }
            view_parent_detail.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            view_parent_detail.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {eventId})", "eventId" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
