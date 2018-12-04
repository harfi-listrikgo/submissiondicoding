package com.example.harfinovian.submission1.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.fragment_match
import com.example.harfinovian.submission1.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        setupViewPager()
        tab_ly.setupWithViewPager(view_pager)
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.populateFragment(ChildMatchFragment().lastmatch("last"), "Last Match")
        adapter.populateFragment(ChildMatchFragment().nextmatch("next"), "Upcoming")
        view_pager.adapter = adapter
    }
}