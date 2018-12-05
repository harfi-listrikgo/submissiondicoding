package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Teams
import io.reactivex.Flowable

interface MatchRepository {
    fun getUpcomingMatch(id: String) : Flowable<Events>
    fun getPastMatch(id: String) : Flowable<Events>
    fun getEventById(id: String) : Flowable<Events>
    fun getTeam(id: String) : Flowable<Teams>
    fun searchMatches(query: String?) : Flowable<Events>
}