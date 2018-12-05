package com.example.harfinovian.submission1.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.R.layout.fragment_match
import com.example.harfinovian.submission1.adapter.ViewPagerAdapter
import com.example.harfinovian.submission1.view.searchmatch.SearchActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.startActivity

class  MatchFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.populateFragment(MatchNestedFragment().lastmatch("last"), "Last Match")
        adapter.populateFragment(MatchNestedFragment().nextmatch("next"), "Upcoming")
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_match, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)

        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchActivity>(
                        "query" to query,
                        "param" to "match"
                )
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
    }

}
