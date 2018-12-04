package com.example.harfinovian.submission1.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.R.layout.activity_main
import com.example.harfinovian.submission1.R.string.*
import com.example.harfinovian.submission1.view.favorite.FavoriteFragment
import com.example.harfinovian.submission1.view.fragment.ChildMatchFragment
import com.example.harfinovian.submission1.view.fragment.MatchFragment

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        main_toolbar.setTitle(app_name)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.last -> {
                    loadLastFragment(savedInstanceState)
                }
                R.id.teams -> {
                    loadNextFragment(savedInstanceState)
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
                    .replace(R.id.main_container, ChildMatchFragment().lastmatch("last"), ChildMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    override fun loadNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, ChildMatchFragment().nextmatch("last"), ChildMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    override fun loadFavFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }

}