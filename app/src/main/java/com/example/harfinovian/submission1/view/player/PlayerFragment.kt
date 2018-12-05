package com.example.harfinovian.submission1.view.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.fragment_player
import com.example.harfinovian.submission1.adapter.PlayerAdapter
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.PlayerRepositoryImpl
import com.example.harfinovian.submission1.model.Player
import com.example.harfinovian.submission1.model.Team
import com.example.harfinovian.submission1.presenter.player.IPlayerPresenter
import com.example.harfinovian.submission1.presenter.player.PlayerPresenter
import com.example.harfinovian.submission1.utlis.AppSchedulerProvider
import com.example.harfinovian.submission1.view.detail.DetailActivity
import com.example.harfinovian.submission1.view.playerdetail.PlayerDetailActivity
import kotlinx.android.synthetic.main.fragment_player.*
import org.jetbrains.anko.support.v4.startActivity

class PlayerFragment : Fragment(), PlayerView{

    private var refreshData: Boolean = false
    private lateinit var iFragmentPresenter: IPlayerPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_player, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val team: Team? = arguments?.getParcelable("teams")
        initView(team)
    }

    fun initView(team: Team?){
        val service = APIRepository.getClient().create(TheSportDBApi::class.java)
        val request = PlayerRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()

        iFragmentPresenter = PlayerPresenter(this, request, scheduler)
        iFragmentPresenter.getPlayerListData(team!!.idTeam)

        player_list.layoutManager = LinearLayoutManager(context)
    }

    override fun showLoading() {
        refreshData = true
        refreshLayout!!.setRefreshing(true)
    }

    override fun hideLoading() {
        refreshData = false
        refreshLayout.setRefreshing(false)
    }

    override fun showPlayerList(data: List<Player>) {
        player_list.adapter = PlayerAdapter(data){
            this.startActivity<PlayerDetailActivity>(
                    "idPlayer" to it.idPlayer
            )
        }
        hideLoading()
    }

}
