package com.example.harfinovian.submission1.view.searchmatch

import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Team

interface SearchMatchView{
    fun setToolbar()
    fun showLoading()
    fun hideLoading()
    fun displayMatch(matchList: List<Event>)
    fun displayTeam(teamList: List<Team>)
}