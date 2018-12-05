package com.example.harfinovian.submission1.view.searchmatch

import com.example.harfinovian.submission1.model.Event

interface SearchMatchView{
    fun showLoading()
    fun hideLoading()
    fun displayMatch(matchList: List<Event>)
}