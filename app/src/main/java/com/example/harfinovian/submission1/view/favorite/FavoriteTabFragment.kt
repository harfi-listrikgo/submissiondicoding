package com.example.harfinovian.submission1.view.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.fragment_favorite
import com.example.harfinovian.submission1.adapter.FavoriteAdapter
import com.example.harfinovian.submission1.adapter.FavoriteTeamAdapter
import com.example.harfinovian.submission1.view.teamdetail.TeamDetailActivity
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.db.FavoriteTeam
import com.example.harfinovian.submission1.entity.repository.ILocalPresenter
import com.example.harfinovian.submission1.entity.repository.LocalRepositoryCompl
import kotlinx.android.synthetic.main.fragment_nested_match.*
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTabFragment : Fragment(), FavoriteView {

    private var refreshData: Boolean = false
    private lateinit var iFragmentPresenter: ILocalPresenter

    fun match(category: String): FavoriteTabFragment {
        val myFragment = FavoriteTabFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.arguments = args

        return myFragment
    }

    fun team(category: String): FavoriteTabFragment {
        val myFragment = FavoriteTabFragment()

        val args = Bundle()
        args.putString("category", category)
        myFragment.arguments = args

        return myFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val param = arguments!!.getString("category")

        if (param.equals("match")){
            iFragmentPresenter = LocalRepositoryCompl(context)
            this.showFavoriteList(iFragmentPresenter.getFavorite())
            refreshLayout.setOnRefreshListener {
                this.showFavoriteList(iFragmentPresenter.getFavorite())
            }
            event_list.layoutManager = LinearLayoutManager(context)
        } else{
            iFragmentPresenter = LocalRepositoryCompl(context)
            this.showTeamFavorite(iFragmentPresenter.getFavoriteTeam())
            refreshLayout.setOnRefreshListener {
                this.showTeamFavorite(iFragmentPresenter.getFavoriteTeam())
            }
            event_list.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val myFragment = inflater.inflate(fragment_favorite, container, false)

        return myFragment
    }

    override fun showFavoriteList(data: List<Favorite>) {
        event_list.adapter = FavoriteAdapter(data){
            this.startActivity<TeamDetailActivity>(
                    "idHome" to it.teamHomeId,
                    "idAway" to it.teamAwayId,
                    "idEvent" to it.eventId)
        }
        hideLoading()
    }

    override fun showTeamFavorite(data: List<FavoriteTeam>) {
        event_list.adapter = FavoriteTeamAdapter(data){
            this.startActivity<TeamDetailActivity>(
                    "idTeam" to it.teamId
            )
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