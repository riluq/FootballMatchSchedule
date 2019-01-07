package com.riluq.footballmatchschedule.database

data class FavoriteTeam(
        val idTeamPrimary: Long?,
        val teamId: String?,
        val teamName: String?,
        val teamBadge: String?) {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID_TEAM_PRIMARY: String = "ID_TEAM_PRIMARY_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }
}