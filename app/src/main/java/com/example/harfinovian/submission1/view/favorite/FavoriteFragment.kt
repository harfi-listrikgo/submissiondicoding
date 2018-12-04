package com.example.harfinovian.submission1.view.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.fragment_match_nested
import com.example.harfinovian.submission1.adapter.FavoriteAdapter
import com.example.harfinovian.submission1.view.detail.DetailActivity
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.repository.ILocalPresenter
import com.example.harfinovian.submission1.entity.repository.LocalRepositoryCompl
import com.example.harfinovian.submission1.view.fragment.FavoriteView
import kotlinx.android.synthetic.main.fragment_match_nested.*
import org.jetbrains.anko.support.v4.startActivity

class FavoriteFragment : Fragment(), FavoriteView {

    private var refreshData: Boolean = false
    private lateinit var iFragmentPresenter: ILocalPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iFragmentPresenter = LocalRepositoryCompl(context)
        this.showFavoriteList(iFragmentPresenter.getFavorite())
        refreshLayout.setOnRefreshListener {
            this.showFavoriteList(iFragmentPresenter.getFavorite())
        }
        event_list.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val myFragment = inflater.inflate(fragment_match_nested, container, false)

        return myFragment
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