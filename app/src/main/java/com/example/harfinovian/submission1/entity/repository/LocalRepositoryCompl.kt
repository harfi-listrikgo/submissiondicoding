package com.example.harfinovian.submission1.entity.repository

import android.content.Context
import android.util.Log
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.db.FavoriteTeam
import com.example.harfinovian.submission1.entity.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import java.lang.Exception

class LocalRepositoryCompl(private val context: Context?) : ILocalPresenter {

    override fun getFavorite() : List<Favorite> {
        lateinit var favoriteList :List<Favorite>
        context!!.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            favoriteList = result.parseList(classParser<Favorite>())
        }

        return favoriteList
    }

    override fun getFavoriteTeam() : List<FavoriteTeam> {
        var favoriteList :List<FavoriteTeam> = listOf()
        context!!.database.use {
            try{
                val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                favoriteList = result.parseList(classParser<FavoriteTeam>())
            } catch (e: Exception){
                Log.e("Error", "Failed parse")
            }
        }

        return favoriteList
    }

}