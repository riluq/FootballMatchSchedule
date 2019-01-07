package com.riluq.footballmatchschedule.database

data class Favorite(val id: Long?,
                    val eventId: String?,
                    val teamHomeId: String?,
                    val teamAwayId: String?,
                    val teamHomeName: String?,
                    val teamAwayName: String?,
                    val teamHomeScore: String?,
                    val teamAwayScore: String?,
                    val dateEvent: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
        const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
        const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
        const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
        const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"

    }

}