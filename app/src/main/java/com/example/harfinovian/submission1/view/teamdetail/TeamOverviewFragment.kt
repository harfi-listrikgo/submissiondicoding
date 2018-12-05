package com.example.harfinovian.submission1.view.teamdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.R.layout.fragment_team_overview
import com.example.harfinovian.submission1.model.Team
import kotlinx.android.synthetic.main.fragment_team_overview.*

class TeamOverviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_team_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val team: Team? = arguments!!.getParcelable("teams")
        initView(team)
    }

    fun initView(teamInfo: Team?){
        Glide.with(this)
                .load(teamInfo?.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(imgBadge)

        teamName.text = teamInfo?.strTeam
        tvManager.text = teamInfo?.strManager
        tvStadium.text = teamInfo?.strStadium
        teamOverview.text = teamInfo?.strDescriptionEN
    }

}
