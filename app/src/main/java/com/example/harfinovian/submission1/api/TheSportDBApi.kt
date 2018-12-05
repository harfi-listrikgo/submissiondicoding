package com.example.harfinovian.submission1.api

import com.example.harfinovian.submission1.model.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportDBApi {
    @GET("eventspastleague.php")
    fun getPastEvent(@Query("id") id: String): Flowable<Events>

    @GET("eventsnextleague.php")
    fun getNextEvent(@Query("id") id: String): Flowable<Events>

    @GET("lookupevent.php")
    fun getEvent(@Query("id") id: String): Flowable<Events>

    @GET("searchevents.php")
    fun searchMatches(@Query("e") query: String) : Flowable<Events>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id:String): Flowable<Teams>

    @GET("searchteams.php")
    fun getTeamBySearch(@Query("t") query: String) : Flowable<Teams>

    @GET("lookup_all_teams.php")
    fun getAllTeam(@Query("id") id:String) : Flowable<Teams>

    @GET("lookup_all_players.php")
    fun getAllPlayers(@Query("id") id:String?) : Flowable<Players>

    @GET("lookupplayer.php")
    fun getPlayerDetail(@Query("id") id:String?) : Flowable<Players>
}