package com.riluq.footballmatchschedule

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.riluq.footballmatchschedule.utils.*
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.startActivity

class Main2Activity : AppCompatActivity(){
    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mSectionsPagerAdapter2: SectionsPagerAdapter2? = null

    private var searchIdIntent: String = SEARCH_MATCH_ID

    var searhItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        // Set up the ViewPager with the sections adapter.

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mSectionsPagerAdapter2 = SectionsPagerAdapter2(supportFragmentManager)

        view_pager.adapter = mSectionsPagerAdapter
        view_pager2.adapter = mSectionsPagerAdapter2

        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        view_pager2.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager2))

        navigation_dua.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_prev_match -> {
                    loadPrevMatchFragment(savedInstanceState)
                }
                R.id.navigation_next_match -> {
                    loadTeamFragment(savedInstanceState)
                }
                R.id.navigation_favorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        navigation_dua.selectedItemId = R.id.navigation_prev_match

    }

    private fun loadPrevMatchFragment(savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.match)
        searchIdIntent = SEARCH_MATCH_ID
        tabId = tabMatch
        searhItem?.isVisible = true
        if (savedInstanceState == null) {

            tabs.removeAllTabs()
            tabs.addTab(tabs.newTab().setText(getString(R.string.prev_match)))
            tabs.addTab(tabs.newTab().setText(getString(R.string.next_match)))

            view_pager.visible()
            tabs.visible()
            container_dua.gone()
            view_pager2.gone()

        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.teams)
        searchIdIntent = SEARCH_TEAM_ID
        searhItem?.isVisible = true
        if (savedInstanceState == null) {
            view_pager.gone()
            view_pager2.gone()
            tabs.gone()
            container_dua.visible()

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_dua, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()

        }


    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.favorite)
        tabId = tabFavorite
        searhItem?.isVisible = false
        if (savedInstanceState == null) {
            tabs.removeAllTabs()
            tabs.addTab(tabs.newTab().setText(getText(R.string.match)))
            tabs.addTab(tabs.newTab().setText(getText(R.string.teams)))

            view_pager.gone()
            view_pager2.visible()
            tabs.visible()
            container_dua.gone()


        }
    }

    private fun searchQuery(searchView: android.support.v7.widget.SearchView) {
        searchView.setOnQueryTextListener(object: android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                startActivity<SearchResultActivity>(
                        INTENT_SEARCH to query,
                        SEARCH_ID to searchIdIntent
                )

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main2, menu)
        searhItem = menu.findItem(R.id.searchMenu)
        val searchView: android.support.v7.widget.SearchView = searhItem?.actionView as android.support.v7.widget.SearchView
        searchView.queryHint = "Search Match"

        searchQuery(searchView)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            return when(position) {
                0 -> return PrevMatchFragment()
                1 -> return NextMatchFragment()
                else -> {
                    return NextMatchFragment()
                }
            }
        }
        override fun getCount(): Int {
            // Show 3 total pages.
            return 2
        }


    }

    class SectionsPagerAdapter2(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            return when(position) {
                0 -> return FavoriteMatchFragment()
                1 -> return FavoriteTeamFragment()
                else -> {
                    return FavoriteTeamFragment()
                }
            }
        }
        override fun getCount(): Int {
            // Show 3 total pages.
            return 2
        }


    }

}
