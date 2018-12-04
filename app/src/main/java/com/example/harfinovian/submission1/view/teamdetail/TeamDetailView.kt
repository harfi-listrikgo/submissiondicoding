package com.example.harfinovian.submission1.view.teamdetail

import android.widget.ImageView
import com.example.harfinovian.submission1.model.Team

interface TeamDetailView {
    fun setToolbar()
    fun bindView(res: Team)
    fun showLogo(url: String?, imageView: ImageView)
}