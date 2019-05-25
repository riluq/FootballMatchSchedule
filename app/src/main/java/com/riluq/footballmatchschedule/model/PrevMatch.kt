package com.riluq.footballmatchschedule.model

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
        var teamAwayScore: String? = null,


        @SerializedName("idHomeTeam")
        var teamIdHome: String? = null,

        @SerializedName("idAwayTeam")
        var teamIdAway: String? = null,

        @SerializedName("strHomeGoalDetails")
        var teamGoalHome: String? = null,

        @SerializedName("strAwayGoalDetails")
        var teamGoalAway: String? = null,

        @SerializedName("intHomeShots")
        var teamHomeShots: String? = null,

        @SerializedName("intAwayShots")
        var teamAwayShots: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var teamHomeGoalkeeper: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var teamAwayGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var teamHomeDefense: String? = null,

        @SerializedName("strAwayLineupDefense")
        var teamAwayDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var teamHomeMidfield: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var teamAwayMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var teamHomeForward: String? = null,

        @SerializedName("strAwayLineupForward")
        var teamAwayForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var teamHomeSubtitutes: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var teamAwaySubtitutes: String? = null,

        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("strHomeFormation")
        var formationHome: String? = null,

        @SerializedName("strAwayFormation")
        var formationAway: String? = null

)