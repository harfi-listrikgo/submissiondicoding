package com.example.harfinovian.submission1.presenter.detail

import android.widget.ImageView

interface IDetailPresenter {
    fun getMatchDetail(idEvent: String?)
    fun showLogo(param: String?, imageView: ImageView)
}