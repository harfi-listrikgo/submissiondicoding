package com.example.harfinovian.submission1.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.R
import com.example.harfinovian.submission1.R.array.league_list
import com.example.harfinovian.submission1.R.layout.fragment_nested_match
import com.example.harfinovian.submission1.adapter.FavoriteAdapter
import com.example.harfinovian.submission1.view.teamdetail.TeamDetailActivity
import com.example.harfinovian.submission1.adapter.ScoreAdapter
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.presenter.match.IMatchPresenter
import com.example.harfinovian.submission1.presenter.match.MatchPresenter
import com.example.harfinovian.submission1.utlis.AppSchedulerProvider
import com.example.harfinovian.submission1.view.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_nested_match.*
import org.jetbrains.anko.support.v4.startActivity

class MatchNestedFragment : Fragment(), MatchView {

    private var refreshData: Boolean = false
    private lateinit var leagueName : String
    private lateinit var iFragmentPresenter: IMatchPresenter

    fun lastmatch(category: String): MatchNestedFragment {
        val myFragment = MatchNestedFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.arguments = args

        return myFragment
    }

    fun nextmatch(category: String): MatchNestedFragment {
        val myFragment = MatchNestedFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.arguments = args

        return myFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = APIRepository.getClient().create(TheSportDBApi::class.java)
        val request = MatchRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        iFragmentPresenter = MatchPresenter(this, request, scheduler)

        val param = arguments!!.getString("category")

        iFragmentPresenter.getFootballMatchData(param, "4328")

        val spinnerItems = resources.getStringArray(league_list)
        val spinnerAdapter = ArrayAdapter(context, R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_league.adapter = spinnerAdapter
        spinner_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_league.selectedItem.toString()
                when(leagueName){
                    "English Premier League" -> iFragmentPresenter.getFootballMatchData(param, "4328")
                    "German Bundesliga" -> iFragmentPresenter.getFootballMatchData(param,"4331")
                    "Italian Serie A" -> iFragmentPresenter.getFootballMatchData(param,"4332")
                    "French Ligue 1" -> iFragmentPresenter.getFootballMatchData(param,"4334")
                    "Spanish La Liga" -> iFragmentPresenter.getFootballMatchData(param,"4335")
                    "Netherlands Eredivisie" -> iFragmentPresenter.getFootballMatchData(param,"4337")
                    else -> iFragmentPresenter.getFootballMatchData(param,"4328")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        refreshLayout.setOnRefreshListener {
            iFragmentPresenter.getFootballMatchData(param, "4328")
        }
        event_list.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val myFragment = inflater.inflate(fragment_nested_match, container, false)

        return myFragment
    }

    override fun showEventList(data: List<Event>) {
        event_list.adapter = ScoreAdapter(data){
            this.startActivity<DetailActivity>(
                    "idHome" to it.idHomeTeam,
                    "idAway" to it.idAwayTeam,
                    "idEvent" to it.idEvent)
        }
        hideLoading()
    }

    override fun showFavoriteList(data: List<Favorite>) {
        event_list.adapter = FavoriteAdapter(data){
            this.startActivity<DetailActivity>(
                    "idHome" to it.teamHomeId,
                    "idAway" to it.teamAwayId,
                    "idEvent" to it.eventId)
        }
        hideLoading()
    }

    override fun showLoading() {
        refreshData = true
        refreshLayout!!.setRefreshing(true)
    }

    override fun hideLoading() {
        refreshData = false
        refreshLayout.setRefreshing(false)
    }
}