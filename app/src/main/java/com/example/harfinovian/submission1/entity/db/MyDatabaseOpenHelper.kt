package com.example.harfinovian.submission1.entity.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by root on 2/6/18.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance ?: MyDatabaseOpenHelper(ctx)
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_EVENT to TEXT + UNIQUE,
                Favorite.TEAM_HOME_ID to TEXT,
                Favorite.TEAM_AWAY_ID to TEXT,
                Favorite.HOME_TEAM to TEXT,
                Favorite.AWAY_TEAM to TEXT,
                Favorite.SCORE_HOME_TEAM to TEXT,
                Favorite.SCORE_AWAY_TEAM to TEXT,
                Favorite.DATE to TEXT)
        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.ID_TEAM to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_IMAGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)