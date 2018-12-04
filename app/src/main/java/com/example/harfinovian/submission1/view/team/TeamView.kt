package com.example.harfinovian.submission1.view.team

import com.example.harfinovian.submission1.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
//    fun showFavoriteList(data: List<TeamFavorite>)
}