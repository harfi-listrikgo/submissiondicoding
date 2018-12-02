package com.example.harfinovian.submission1.entity.repository

import com.example.harfinovian.submission1.entity.db.Favorite

interface ILocalPresenter {
    fun getFavorite() : List<Favorite>
}