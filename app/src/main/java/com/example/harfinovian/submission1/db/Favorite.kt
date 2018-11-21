package com.dicoding.kotlinacademy.db

data class Favorite(val id: Long?, val eventId: String, val teamHomeId: String?, val teamAwayId: String?, val homeTeam: String?, val awayTeam: String?,
                    val scoreHome: String?, val scoreAway: String?, val date: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val SCORE_HOME_TEAM: String = "SCORE_HOME_TEAM"
        const val SCORE_AWAY_TEAM: String = "SCORE_AWAY_TEAM"
        const val DATE: String = "DATE"
    }
}