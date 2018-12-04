package com.example.harfinovian.submission1.view.main

import android.os.Bundle

interface MainView {
    fun loadLastFragment(savedInstanceState: Bundle?)
    fun loadTeamFragment(savedInstanceState: Bundle?)
    fun loadFavFragment(savedInstanceState: Bundle?)
}