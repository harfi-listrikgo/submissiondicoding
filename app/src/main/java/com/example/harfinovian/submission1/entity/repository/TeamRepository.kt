package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.model.Teams
import io.reactivex.Flowable

interface TeamRepository {
    fun getTeam(id: String) : Flowable<Teams>
    fun getListTeam(idLeague: String) : Flowable<Teams>
}