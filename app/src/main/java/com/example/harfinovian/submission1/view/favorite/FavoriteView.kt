package com.example.harfinovian.submission1.view.fragment

import com.example.harfinovian.submission1.entity.db.Favorite

interface FavoriteView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteList(data: List<Favorite>)
}