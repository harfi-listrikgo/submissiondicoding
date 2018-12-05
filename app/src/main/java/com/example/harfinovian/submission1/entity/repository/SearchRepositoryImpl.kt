package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.model.Teams
import io.reactivex.Flowable

class SearchRepositoryImpl(private val footballRest: TheSportDBApi) : SearchRepository {

    override fun searchMatches(query: String?) = footballRest.searchMatches(query)
    override fun searchTeam(query: String?) = footballRest.getTeamBySearch(query)
}