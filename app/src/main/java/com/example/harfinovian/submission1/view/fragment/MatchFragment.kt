package com.example.harfinovian.submission1.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.fragment_match
import com.example.harfinovian.submission1.view.detail.DetailActivity
import com.example.harfinovian.submission1.adapter.ScoreAdapter
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.presenter.match.IMatchPresenter
import com.example.harfinovian.submission1.presenter.match.MatchPresenterCompl
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment : Fragment(), MatchView {

    private var refreshData: Boolean = false
    private var iFragmentPresenter: IMatchPresenter? = null

    fun lastmatch(category: String): MatchFragment {
        val myFragment = MatchFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.setArguments(args)

        return myFragment
    }

    fun nextmatch(category: String): MatchFragment {
        val myFragment = MatchFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.setArguments(args)

        return myFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val myFragment = inflater!!.inflate(fragment_match, container, false)
        iFragmentPresenter = MatchPresenterCompl(this)

        return myFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val param = arguments!!.getString("category")

        iFragmentPresenter?.getAllItemList(view, param)
        refreshLayout.setOnRefreshListener {
            iFragmentPresenter?.getAllItemList(view, param)
        }
        event_list.layoutManager = LinearLayoutManager(context)
    }

    override fun showEventList(data: Events, view: View) {
        event_list.adapter = ScoreAdapter(view.context, data!!.events){
            this.startActivity<DetailActivity>(
                    "idHome" to it.idHomeTeam,
                    "idAway" to it.idAwayTeam,
                    "idEvent" to it.idEvent)
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