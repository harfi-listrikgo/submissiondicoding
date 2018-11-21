package com.example.harfinovian.submission1.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.kotlinacademy.db.Favorite
import com.example.harfinovian.submission1.R.layout.fragment_match
import com.example.harfinovian.submission1.adapter.FavoriteAdapter
import com.example.harfinovian.submission1.view.detail.DetailActivity
import com.example.harfinovian.submission1.adapter.ScoreAdapter
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.presenter.match.IMatchPresenter
import com.example.harfinovian.submission1.presenter.match.MatchPresenterCompl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment : Fragment(), MatchView {

    private var refreshData: Boolean = false
    private var iFragmentPresenter: IMatchPresenter? = null

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

    fun favmatch(category: String): MatchFragment {
        val myFragment = MatchFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.arguments = args

        return myFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val myFragment = layoutInflater.inflate(fragment_match, container, false)
        iFragmentPresenter = MatchPresenterCompl(this)

        val param = arguments!!.getString("category")

        iFragmentPresenter?.getAllItemList(myFragment, param)
        refreshLayout.setOnRefreshListener {
            iFragmentPresenter?.getAllItemList(myFragment, param)
        }
        event_list.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val myFragment = inflater.inflate(fragment_match, container, false)

        return myFragment
    }

    override fun showEventList(data: Events, view: View) {
        event_list.adapter = ScoreAdapter(view.context, data.events){
            this.startActivity<DetailActivity>(
                    "idHome" to it.idHomeTeam,
                    "idAway" to it.idAwayTeam,
                    "idEvent" to it.idEvent)
        }
        hideLoading()
    }

    override fun showFavoriteList(data: List<Favorite>, view: View) {
        event_list.adapter = FavoriteAdapter(view.context, data){
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