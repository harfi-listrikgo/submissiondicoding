package com.example.harfinovian.submission1.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.fragment_match
import com.example.harfinovian.submission1.adapter.FavoriteAdapter
import com.example.harfinovian.submission1.view.detail.DetailActivity
import com.example.harfinovian.submission1.adapter.ScoreAdapter
import com.example.harfinovian.submission1.adapter.ViewPagerAdapter
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.presenter.match.IMatchPresenter
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)

        setHasOptionsMenu(true)
        adapter.populateFragment(ChildMatchFragment().lastmatch("last"), "Last Match")
        adapter.populateFragment(ChildMatchFragment().nextmatch("next"), "Upcoming")
        view_pager.adapter = adapter
        tab_ly.setupWithViewPager(view_pager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val myFragment = inflater.inflate(fragment_match, container, false)

        return myFragment
    }

}