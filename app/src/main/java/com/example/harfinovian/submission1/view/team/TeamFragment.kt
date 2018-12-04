package com.example.harfinovian.submission1.view.team

import android.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.harfinovian.submission1.R.array.league_list
import com.example.harfinovian.submission1.R.layout.fragment_team
import com.example.harfinovian.submission1.adapter.TeamAdapter
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.TeamRepositoryImpl
import com.example.harfinovian.submission1.model.Team
import com.example.harfinovian.submission1.presenter.team.ITeamPresenter
import com.example.harfinovian.submission1.presenter.team.TeamPresenter
import com.example.harfinovian.submission1.utlis.AppSchedulerProvider
import com.example.harfinovian.submission1.view.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment : Fragment(), TeamView {

    private var refreshData: Boolean = false
    lateinit var leagueName : String
    private lateinit var iFragmentPresenter: ITeamPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = APIRepository.getClient().create(TheSportDBApi::class.java)
        val request = TeamRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()

        iFragmentPresenter = TeamPresenter(this, request, scheduler)
        iFragmentPresenter.getFootballTeamData("4328")

        val spinnerItems = resources.getStringArray(league_list)
        val spinnerAdapter = ArrayAdapter(context, R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_team.adapter = spinnerAdapter
        spinner_team.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_team.selectedItem.toString()
                when(leagueName){
                    "English Premier League" -> iFragmentPresenter.getFootballTeamData("4328")
                    "German Bundesliga" -> iFragmentPresenter.getFootballTeamData("4331")
                    "Italian Serie A" -> iFragmentPresenter.getFootballTeamData("4332")
                    "French Ligue 1" -> iFragmentPresenter.getFootballTeamData("4334")
                    "Spanish La Liga" -> iFragmentPresenter.getFootballTeamData("4335")
                    "Netherlands Eredivisie" -> iFragmentPresenter.getFootballTeamData("4337")
                    else -> iFragmentPresenter.getFootballTeamData("4328")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        refreshLayout.setOnRefreshListener {
            iFragmentPresenter.getFootballTeamData("4328")
        }
        team_list.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val myFragment = inflater.inflate(fragment_team, container, false)

        return myFragment
    }

    override fun showTeamList(data: List<Team>) {
        team_list.adapter = TeamAdapter(data){
            this.startActivity<TeamDetailActivity>(
                    "idTeam" to it.idTeam)
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