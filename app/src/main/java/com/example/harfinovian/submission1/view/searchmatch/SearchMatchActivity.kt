package com.example.harfinovian.submission1.view.searchmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.adapter.ScoreAdapter
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.presenter.searchmatch.SearchPresenter
import com.example.harfinovian.submission1.utlis.AppSchedulerProvider
import com.example.harfinovian.submission1.utlis.hide
import com.example.harfinovian.submission1.utlis.show
import com.example.harfinovian.submission1.view.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {

    private var matchLists : MutableList<Event> = mutableListOf()
    lateinit var mPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        val query = intent.getStringExtra("query")

        val service = APIRepository.getClient().create(TheSportDBApi::class.java)
        val request = MatchRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = SearchPresenter(this, request, scheduler)
        mPresenter.searchMatch(query)
    }

    override fun showLoading() {
        progress_circular.show()
        football_list.hide()
    }

    override fun hideLoading() {
        progress_circular.hide()
        football_list.show()
    }

    override fun displayMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        football_list.layoutManager = layoutManager
        football_list.adapter = ScoreAdapter(matchList){
            this.startActivity<DetailActivity>(
                    "idHome" to it.idHomeTeam,
                    "idAway" to it.idAwayTeam,
                    "idEvent" to it.idEvent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mPresenter.searchMatch(newText)
                return false
            }
        })

        return true
    }

}
