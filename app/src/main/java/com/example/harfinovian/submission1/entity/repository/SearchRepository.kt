package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Teams
import io.reactivex.Flowable

interface SearchRepository {
    fun searchMatches(query: String?) : Flowable<Events>
    fun searchTeam(query: String?) : Flowable<Teams>
}