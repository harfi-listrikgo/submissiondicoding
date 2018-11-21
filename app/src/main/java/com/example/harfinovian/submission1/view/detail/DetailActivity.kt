package com.example.harfinovian.submission1.view.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.harfinovian.submission1.R.layout.activity_detail
import com.example.harfinovian.submission1.R.layout.text_prop_match_detail
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.presenter.detail.DetailPresenterCompl
import com.example.harfinovian.submission1.presenter.detail.IDetailPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.text_prop_match_detail.view.*

class DetailActivity : AppCompatActivity(), DetailView {

    private var iFragmentPresenter: IDetailPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail)

        setToolbar()

        val idHome = intent.getStringExtra("idHome")
        val idAway = intent.getStringExtra("idAway")
        val idEvent = intent.getStringExtra("idEvent")

        iFragmentPresenter =  DetailPresenterCompl(this)
        iFragmentPresenter?.getMatchDetail(idEvent)
        iFragmentPresenter?.showLogo(idHome, home_img)
        iFragmentPresenter?.showLogo(idAway, away_img)
    }

    override fun setToolbar() {
        setSupportActionBar(main_toolbar)
        val ab = supportActionBar
        ab?.setTitle("Match Detail")
        ab?.setDisplayShowHomeEnabled(true)
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    override fun bindView(res: Event) {
        date_txt.text = res.dateEvent
        score_home_txt.text = res.intHomeScore
        score_away_txt.text = res.intAwayScore
        home_team_txt.text = res.strHomeTeam
        away_team_txt.text = res.strAwayTeam

        content_ly.addView(addDynamicView("Goals", res.strHomeGoalDetails, res.strAwayGoalDetails))
        content_ly.addView(addDynamicView("Shots", res.intHomeShots, res.intAwayShots))
        detail_ly.addView(addDynamicView("Goal Keeper", res.strHomeLineupGoalkeeper, res.strAwayLineupGoalkeeper))
        detail_ly.addView(addDynamicView("Defense", res.strHomeLineupDefense, res.strAwayLineupDefense))
        detail_ly.addView(addDynamicView("Midfield", res.strHomeLineupMidfield, res.strAwayLineupMidfield))
        detail_ly.addView(addDynamicView("Forward", res.strHomeLineupForward, res.strAwayLineupForward))
        detail_ly.addView(addDynamicView("Subtitutes", res.strHomeLineupSubstitutes, res.strAwayLineupSubstitutes))
    }

    override fun showLogo(url: String?, imageView : ImageView) {
        Glide.with(this).load(url).into(imageView)
    }

    private fun addDynamicView(title: String?, homeProp: String?, awayProp: String?): LinearLayout {
        var linearLayout = View.inflate(this,
                text_prop_match_detail, null) as LinearLayout

        linearLayout.title_txt?.text = title
        val home = homeProp?.split(";")
        home?.forEach {
            linearLayout.home_txt.append(it.trim() + "\n")
        }

        val away = awayProp?.split(";")
        away?.forEach {
            linearLayout.away_txt.append(it.trim() + "\n")
        }

        return linearLayout
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
