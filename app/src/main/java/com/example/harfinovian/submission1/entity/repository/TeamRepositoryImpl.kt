package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.model.Teams
import io.reactivex.Flowable

class TeamRepositoryImpl(private val footballRest: TheSportDBApi) : TeamRepository {

    override fun getTeam(id: String): Flowable<Teams> = footballRest.getTeam(id)

    override fun getListTeam(idLeague: String): Flowable<Teams> = footballRest.getAllTeam(idLeague)

}