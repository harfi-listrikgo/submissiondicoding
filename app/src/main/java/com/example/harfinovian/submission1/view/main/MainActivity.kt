package com.example.harfinovian.submission1.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.R.layout.activity_main
import com.example.harfinovian.submission1.R.string.*
import com.example.harfinovian.submission1.view.fragment.MatchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        main_toolbar.setTitle(app_name)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.last -> {
                    loadLastFragment(savedInstanceState)
                }
                R.id.next -> {
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

    private fun loadLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment().lastmatch("last"), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment().nextmatch("next"), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment().favmatch("fav"), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

}