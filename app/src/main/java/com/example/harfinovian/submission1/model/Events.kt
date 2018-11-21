package com.example.harfinovian.submission1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Events (
        var events: List<Event> = listOf()
)

@Parcelize
data class Event (
        var idEvent: String?,
        var strEvent: String?,
        var strFilename: String?,
        var strSport: String?,
        var idLeague: String?,
        var strLeague: String?,
        var strSeason: String?,
        var strDescriptionEN: String?,
        var strHomeTeam: String?,
        var strAwayTeam: String?,
        var intHomeScore: String?,
        var intRound: String?,
        var intAwayScore: String?,
        var intSpectators: String?,
        var strHomeGoalDetails: String?,
        var strHomeRedCards: String?,
        var strHomeYellowCards: String?,
        var strHomeLineupGoalkeeper: String?,
        var strHomeLineupDefense: String?,
        var strHomeLineupMidfield: String?,
        var strHomeLineupForward: String?,
        var strHomeLineupSubstitutes: String?,
        var strHomeFormation: String?,
        var strAwayRedCards: String?,
        var strAwayYellowCards: String?,
        var strAwayGoalDetails: String?,
        var strAwayLineupGoalkeeper: String?,
        var strAwayLineupDefense: String?,
        var strAwayLineupMidfield: String?,
        var strAwayLineupForward: String?,
        var strAwayLineupSubstitutes: String?,
        var strAwayFormation: String?,
        var intHomeShots: String?,
        var intAwayShots: String?,
        var dateEvent: String?,
        var strDate: String?,
        var strTime: String?,
        var strTVStation: String?,
        var idHomeTeam: String?,
        var idAwayTeam: String?,
        var strResult: String?,
        var strCircuit: String?,
        var strCountry: String?,
        var strCity: String?,
        var strPoster: String?,
        var strFanart: String?,
        var strThumb: String?,
        var strBanner: String?,
        var strMap: String?,
        var strLocked: String?
) : Parcelable