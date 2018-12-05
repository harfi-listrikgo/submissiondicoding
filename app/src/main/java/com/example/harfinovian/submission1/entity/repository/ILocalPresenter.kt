package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.db.FavoriteTeam

interface ILocalPresenter {
    fun getFavorite() : List<Favorite>
    fun getFavoriteTeam() : List<FavoriteTeam>
}