package com.riluq.footballmatchschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.database.Favorite
import com.riluq.footballmatchschedule.database.FavoriteTeam
import com.riluq.footballmatchschedule.model.Players
import com.riluq.footballmatchschedule.model.Team
import com.riluq.footballmatchschedule.presenter.TeamDetailPresenter
import com.riluq.footballmatchschedule.utils.database
import com.riluq.footballmatchschedule.utils.invisible
import com.riluq.footballmatchschedule.utils.visible
import com.riluq.footballmatchschedule.view.TeamDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail2.*
import kotlinx.android.synthetic.main.fragment_overview_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetail2Activity : AppCompatActivity(), TeamDetailView {
    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private lateinit var idTeams: String

    private lateinit var presenter: TeamDetailPresenter

    private lateinit var teams: Team

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail2)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Team Detail"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idTeams = intent.getStringExtra("id")

        favoriteState()

        mSectionsPagerAdapter = SectionsPagerAdapter(idTeams, 2, supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

//        adapterPlayers = PlayersAdapter(applicationContext, players) {
//            startActivity<TeamDetail2Activity>("id" to "${it.playerId}")
//        }



        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(idTeams)
//        swipeRefreshLayout.onRefresh {
//            presenter.getTeamDetail(id)
//        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to teams.teamId,
                        FavoriteTeam.TEAM_NAME to teams.teamName,
                        FavoriteTeam.TEAM_BADGE to teams.teamBadge)
            }
            snackbar(main_content, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(main_content, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to idTeams)
            }
            snackbar(main_content, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(main_content, e.localizedMessage).show()
        }
    }
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to idTeams)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter(private val idTeam: String, private val tabCount: Int, fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> return newInstance(idTeam)
                1 -> return newInstancePlayer(idTeam)
                else -> {
                    return PlayersTeamFragment()
                }
            }
        }


        override fun getCount(): Int {
            return tabCount
        }

        companion object {
            const val KEY_TEAM = "KEY_TEAM"
            const val KEY_TEAM_2 = "KEY_TEAM_2"
            fun newInstance(id: String): OverviewTeamFragment {
                val bindData = Bundle()
                bindData.putString(KEY_TEAM, id)

                val desciptionTeamFragment = OverviewTeamFragment()
                desciptionTeamFragment.arguments = bindData
                return desciptionTeamFragment
            }

            fun newInstancePlayer(id: String): PlayersTeamFragment {
                val bindData = Bundle()
                bindData.putString(KEY_TEAM_2, id)

                val playerTeamFragment = PlayersTeamFragment()
                playerTeamFragment.arguments = bindData
                return playerTeamFragment
            }
        }

    }

    override fun showLoading() {
        pb_team_detail.visible()
    }

    override fun hideLoading() {
        pb_team_detail.invisible()
    }

//    override fun showTeamDetail(data: List<Team>) {
//        //display data on UI
//        teams = Team(data[0].teamId,
//                data[0].teamName,
//                data[0].teamBadge)
////        swipeRefreshLayout.isRefreshing = false
//        Picasso.get().load(data[0].teamBadge).into(img_team_badge)
//        tv_team_name.text = data[0].teamName
//        tv_team_description.text = data[0].teamDescription
//        tv_team_formed_year.text = data[0].teamFormedYear
//        tv_team_stadium.text = data[0].teamStadium
//    }

    override fun showTeamDetail(data: List<Team>, dataPlayers: List<Players>) {
        //display data on UI
        teams = Team(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge)
//        swipeRefreshLayout.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(img_team_badge)
        tv_team_name.text = data[0].teamName
        tv_team_description.text = data[0].teamDescription
        tv_team_formed_year.text = data[0].teamFormedYear
        tv_team_stadium.text = data[0].teamStadium


    }
}
