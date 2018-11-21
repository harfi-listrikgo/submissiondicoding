package com.example.harfinovian.submission1.view.fragment

import android.view.View
import com.example.harfinovian.submission1.model.Events

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: Events, view: View)
}