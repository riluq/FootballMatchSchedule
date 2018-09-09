package com.riluq.footballmatchschedule

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_match -> {
                prevMatchFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                nextMatchFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun prevMatchFragment() {
//        toolbar.title = getString(R.string.prev_match)
        val prevMatchFragment = PrevMatchFragment.newInstance()
        openFragment(prevMatchFragment)
    }

    fun nextMatchFragment() {
//        toolbar.title = getString(R.string.next_match)
        val nextMatchFragment = NextMatchFragment.newInstance()
        openFragment(nextMatchFragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun homeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeFragment(PrevMatchFragment.newInstance())
        toolbar = supportActionBar!!
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        toolbar.title = getString(R.string.prev_match)
    }
}
