package com.riluq.footballmatchschedule

import android.net.Uri

object TheSportDBApi {
    fun getPrevMatch(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", "4328")
                .build()
                .toString()

    }
}