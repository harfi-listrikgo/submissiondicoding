package com.example.harfinovian.submission1.presenter.match

import android.view.View

interface IMatchPresenter {
    fun getFootballMatchData(param: String?, id: String)
    fun onDestroy()
}