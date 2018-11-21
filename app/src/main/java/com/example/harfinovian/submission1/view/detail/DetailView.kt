package com.example.harfinovian.submission1.view.detail

import android.widget.ImageView
import com.example.harfinovian.submission1.model.Event

interface DetailView {
    fun setToolbar()
    fun bindView(res: Event)
    fun showLogo(url: String?, imageView: ImageView)
}