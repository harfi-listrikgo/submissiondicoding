package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Team
import com.example.harfinovian.submission1.model.Teams
import io.reactivex.Flowable

interface MatchRepository {
    fun getUpcomingMatch() : Flowable<Events>
    fun getPastMatch() : Flowable<Events>
    fun getEventById(id: String) : Flowable<Events>
    fun getTeam(id: String) : Flowable<Teams>
    fun getListTeam(idLeague: String) : Flowable<Teams>
}