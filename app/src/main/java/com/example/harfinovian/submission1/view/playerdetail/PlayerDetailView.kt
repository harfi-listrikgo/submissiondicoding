package com.example.harfinovian.submission1.view.playerdetail

import com.example.harfinovian.submission1.model.Player

interface PlayerDetailView {
    fun setToolbar(title: String?)
    fun bindView(res: Player)
}