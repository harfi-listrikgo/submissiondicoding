package com.example.harfinovian.submission1.view.player

import com.example.harfinovian.submission1.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
//    fun showFavoriteList(data: List<Favorite>)
}