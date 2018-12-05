package com.example.harfinovian.submission1.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.R.layout.activity_main
import com.example.harfinovian.submission1.R.string.*
import com.example.harfinovian.submission1.view.favorite.FavoriteFragment
import com.example.harfinovian.submission1.view.favorite.FavoriteTabFragment
import com.example.harfinovian.submission1.view.fragment.MatchFragment
import com.example.harfinovian.submission1.view.fragment.MatchNestedFragment
import com.example.harfinovian.submission1.view.team.TeamFragment

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        setSupportActionBar(main_toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.last -> {
                    loadLastFragment(savedInstanceState)
                }
                R.id.team -> {
                    loadTeamFragment(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.last
    }

    override fun loadLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchNestedFragment::class.java.simpleName)
                    .commit()
        }
    }

    override fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                    .commit()
        }
    }

    override fun loadFavFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteTabFragment::class.java.simpleName)
                    .commit()
        }
    }

}