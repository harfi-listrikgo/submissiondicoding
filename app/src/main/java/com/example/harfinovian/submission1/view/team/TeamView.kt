package com.example.harfinovian.submission1.view.team

import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
    fun showFavoriteList(data: List<Favorite>)
}