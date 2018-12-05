package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Teams
import io.reactivex.Flowable

class MatchRepositoryImpl(private val footballRest: TheSportDBApi) : MatchRepository {

    override fun getEventById(id: String): Flowable<Events> = footballRest.getEvent(id)

    override fun getUpcomingMatch(id: String): Flowable<Events> = footballRest.getNextEvent(id)

    override fun getPastMatch(id: String): Flowable<Events> = footballRest.getPastEvent(id)

    override fun getTeam(id: String): Flowable<Teams> = footballRest.getTeam(id)

    override fun searchMatches(query: String?) = footballRest.searchMatches(query)
}