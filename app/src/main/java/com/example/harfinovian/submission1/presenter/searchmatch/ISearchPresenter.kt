package com.example.harfinovian.submission1.presenter.searchmatch

interface ISearchPresenter{
    fun searchMatch(query: String?)
    fun searchTeam(query: String?)
    fun onDestroy()
}