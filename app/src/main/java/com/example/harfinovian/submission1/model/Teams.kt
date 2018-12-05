package com.example.harfinovian.submission1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Teams (
        var teams: List<Team> = listOf()
)

@Parcelize
data class Team (
        var idTeam: String?,
        var idSoccerXML: String?,
        var intLoved: String?,
        var strTeam: String?,
        var strTeamShort: String?,
        var strAlternate: String?,
        var intFormedYear: String?,
        var strSport: String?,
        var strLeague: String?,
        var idLeague: String?,
        var strDivision: String?,
        var strManager: String?,
        var strStadium: String?,
        var strKeywords: String?,
        var strRSS: String?,
        var strStadiumThumb: String?,
        var strStadiumDescription: String?,
        var strStadiumLocation: String?,
        var intStadiumCapacity: String?,
        var strWebsite: String?,
        var strFacebook: String?,
        var strTwitter: String?,
        var strInstagram: String?,
        var strDescriptionEN: String?,
        var strDescriptionDE: String?,
        var strDescriptionFR: String?,
        var strDescriptionCN: String?,
        var strDescriptionIT: String?,
        var strDescriptionJP: String?,
        var strDescriptionRU: String?,
        var strDescriptionES: String?,
        var strDescriptionPT: String?,
        var strDescriptionSE: String?,
        var strDescriptionNL: String?,
        var strDescriptionHU: String?,
        var strDescriptionNO: String?,
        var strDescriptionIL: String?,
        var strDescriptionPL: String?,
        var strGender: String?,
        var strCountry: String?,
        var strTeamBadge: String?,
        var strTeamJersey: String?,
        var strTeamLogo: String?,
        var strTeamFanart1: String?,
        var strTeamFanart2: String?,
        var strTeamFanart3: String?,
        var strTeamFanart4: String?,
        var strTeamBanner: String?,
        var strYoutube: String?,
        var strLocked: String?
) : Parcelable