package com.riluq.footballmatchschedule

import com.google.gson.annotations.SerializedName

data class PrevMatch (
        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strHomeTeam")
        var teamHome: String? = null,

        @SerializedName("strAwayTeam")
        var teamAway: String? = null,

        @SerializedName("intHomeScore")
        var teamHomeScore: String? = null,

        @SerializedName("intAwayScore")
        var teamAwayScore: String? = null
)