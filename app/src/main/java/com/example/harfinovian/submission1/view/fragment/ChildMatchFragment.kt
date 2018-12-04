package com.example.harfinovian.submission1.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.fragment_match_nested
import com.example.harfinovian.submission1.adapter.FavoriteAdapter
import com.example.harfinovian.submission1.view.detail.DetailActivity
import com.example.harfinovian.submission1.adapter.ScoreAdapter
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.presenter.match.IMatchPresenter
import com.example.harfinovian.submission1.presenter.match.MatchPresenter
import com.example.harfinovian.submission1.view.detail.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_match_nested.*
import org.jetbrains.anko.support.v4.startActivity

class ChildMatchFragment : Fragment(), MatchView {

    private var refreshData: Boolean = false
    private lateinit var iFragmentPresenter: IMatchPresenter

    fun lastmatch(category: String): MatchFragment {
        val myFragment = MatchFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.arguments = args

        return myFragment
    }

    fun nextmatch(category: String): MatchFragment {
        val myFragment = MatchFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.arguments = args

        return myFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val myFragment = inflater.inflate(fragment_match_nested, container, false)

        return myFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = APIRepository.getClient().create(TheSportDBApi::class.java)
        val request = MatchRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        iFragmentPresenter = MatchPresenter(this, request, scheduler)

        val param = arguments!!.getString("category")

        iFragmentPresenter.getFootballMatchData(param)
        refreshLayout.setOnRefreshListener {
            iFragmentPresenter.getFootballMatchData(param)
        }
        event_list.layoutManager = LinearLayoutManager(context)
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