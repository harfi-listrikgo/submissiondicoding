package com.example.harfinovian.submission1.presenter.detail

import android.widget.ImageView

interface IDetailPresenter {
    fun getMatchDetail(param: String)
    fun showLogo(param: String?, imageView: ImageView)
}