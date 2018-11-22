package com.example.harfinovian.submission1.view.fragment

import android.view.View
import com.example.harfinovian.submission1.db.Favorite
import com.example.harfinovian.submission1.model.Events

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: Events, view: View)
    fun showFavoriteList(data: List<Favorite>, view: View)
}