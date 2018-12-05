package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.model.Players
import io.reactivex.Flowable

interface PlayerRepository {
    fun getPlayer(id: String) : Flowable<Players>
    fun getListPlayer(id: String?) : Flowable<Players>
}