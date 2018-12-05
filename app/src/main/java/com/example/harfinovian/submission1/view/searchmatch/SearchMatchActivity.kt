package com.example.harfinovian.submission1.view.searchmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.R.layout.activity_search_match
import com.example.harfinovian.submission1.adapter.ScoreAdapter
import com.example.harfinovian.submission1.adapter.TeamAdapter
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.entity.repository.SearchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Team
import com.example.harfinovian.submission1.presenter.searchmatch.SearchPresenter
import com.example.harfinovian.submission1.utlis.AppSchedulerProvider
import com.example.harfinovian.submission1.utlis.hide
import com.example.harfinovian.submission1.utlis.show
import com.example.harfinovian.submission1.view.detail.DetailActivity
import com.example.harfinovian.submission1.view.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {

    private var matchLists : MutableList<Event> = mutableListOf()
    private var teamLists : MutableList<Team> = mutableListOf()
    lateinit var mPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_search_match)

        setToolbar()

        val query = intent.getStringExtra("query")
        val param = intent.getStringExtra("param")
        val service = APIRepository.getClient().create(TheSportDBApi::class.java)
        val scheduler = AppSchedulerProvider()

        if(param.equals("match")){

            val request = SearchRepositoryImpl(service)
            mPresenter = SearchPresenter(this, request, scheduler)
            mPresenter.searchMatch(query)
        } else {

            val request = SearchRepositoryImpl(service)
            mPresenter = SearchPresenter(this, request, scheduler)
            mPresenter.searchTeam(query)
        }
    }

    override fun showLoading() {
        progress_circular.show()
        football_list.hide()
    }

    override fun hideLoading() {
        progress_circular.hide()
        football_list.show()
    }

    override fun displayTeam(teamList: List<Team>) {
        teamLists.clear()
        teamLists.addAll(teamList)
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        football_list.layoutManager = layoutManager
        football_list.adapter = TeamAdapter(teamLists){
            this.startActivity<TeamDetailActivity>(
                    "idTeam" to it.idTeam)
        }
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

    override fun setToolbar() {
        setSupportActionBar(main_toolbar)
        val ab = supportActionBar
        ab?.setTitle("Search Matches")
        ab?.setDisplayShowHomeEnabled(true)
        ab?.setDisplayHomeAsUpEnabled(true)
    }

}
