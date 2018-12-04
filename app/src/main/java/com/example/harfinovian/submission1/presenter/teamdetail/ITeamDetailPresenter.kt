package com.example.harfinovian.submission1.presenter.teamdetail

import android.widget.ImageView

interface ITeamDetailPresenter {
    fun getTeamDetail(idEvent: String)
    fun showLogo(param: String, imageView: ImageView)
}