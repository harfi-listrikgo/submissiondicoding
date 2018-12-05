package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.model.Players
import io.reactivex.Flowable

class PlayerRepositoryImpl(private val footballRest: TheSportDBApi) : PlayerRepository {

    override fun getListPlayer(id: String?): Flowable<Players> = footballRest.getAllPlayers(id)

    override fun getPlayer(id: String): Flowable<Players> = footballRest.getPlayerDetail(id)

}