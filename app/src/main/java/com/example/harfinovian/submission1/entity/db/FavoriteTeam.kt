package com.example.harfinovian.submission1.entity.db

data class FavoriteTeam(val id: Long?, val teamId: String, val teamName: String, val teamImage: String) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_IMAGE: String = "TEAM_IMAGE"
    }
}