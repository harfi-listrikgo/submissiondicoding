package com.example.harfinovian.submission1.presenter.teamdetail

import android.widget.ImageView

interface ITeamDetailPresenter {
    fun getTeamDetail(idTeam: String)
    fun showLogo(param: String, imageView: ImageView)
}