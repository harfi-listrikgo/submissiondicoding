package com.example.harfinovian.submission1.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.harfinovian.submission1.R.string.app_name
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.ViewPager
import com.example.harfinovian.submission1.R.layout.activity_main
import com.example.harfinovian.submission1.view.fragment.MatchFragment
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        main_toolbar.setTitle(app_name)

        setupViewPager(viewpager_main)
        tabs_main.setupWithViewPager(viewpager_main)
    }

    override fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(MatchFragment().lastmatch("last"), "Last Match")
        adapter.addFrag(MatchFragment().nextmatch("next"), "Next Match")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}