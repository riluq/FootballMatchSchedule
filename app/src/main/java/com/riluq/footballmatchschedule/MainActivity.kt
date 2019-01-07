package com.riluq.footballmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.riluq.footballmatchschedule.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navigation_prev_match -> {
                    loadPrevMatchFragment(savedInstanceState)
                }
                navigation_next_match -> {
                    loadNextMatchFragment(savedInstanceState)
                }
                navigation_favorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        navigation.selectedItemId = navigation_prev_match
    }
    private fun loadPrevMatchFragment(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.match)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, PrevMatchFragment(), PrevMatchFragment::class.java.simpleName)
                    .commit()
        }
    }



    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.teams)
        supportActionBar?.hide()
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.favorite)
        supportActionBar?.show()
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
}
