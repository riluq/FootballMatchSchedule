package com.riluq.footballmatchschedule.utils

import android.content.Context
import android.view.View
import com.riluq.footballmatchschedule.database.helper.MyDatabaseOpenHelper

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

val NO_PARAMETER: String = "no_parameter"

val INTENT_SEARCH: String = "intent_search"
val SEARCH_ID: String = "search_id"
val SEARCH_MATCH_ID: String = "555"
val SEARCH_TEAM_ID: String = "666"

var tabMatch: Int = 1
var tabFavorite: Int = 2

var tabId: Int= tabMatch