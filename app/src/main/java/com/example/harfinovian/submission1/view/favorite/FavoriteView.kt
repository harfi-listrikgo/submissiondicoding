package com.example.harfinovian.submission1.view.favorite

import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.db.FavoriteTeam

interface FavoriteView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteList(data: List<Favorite>)
    fun showTeamFavorite(data: List<FavoriteTeam>)
}