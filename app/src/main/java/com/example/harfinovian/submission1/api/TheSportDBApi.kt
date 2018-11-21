package com.example.harfinovian.submission1.api

import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Teams
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportDBApi {
    @GET("eventspastleague.php?id=4328")
    fun getPastEvent(): Flowable<Events>

    @GET("eventsnextleague.php?id=4328")
    fun getNextEvent(): Flowable<Events>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id:String?): Flowable<Teams>

    @GET("lookupevent.php")
    fun getEvent(@Query("id") id: String?): Flowable<Events>
}