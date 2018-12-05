package com.example.harfinovian.submission1.view.detail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.harfinovian.submission1.R.drawable.ic_add_to_favorites
import com.example.harfinovian.submission1.R.drawable.ic_added_to_favorites
import com.example.harfinovian.submission1.R.id.add_to_favorite
import com.example.harfinovian.submission1.R.layout.activity_detail
import com.example.harfinovian.submission1.R.layout.text_prop_match_detail
import com.example.harfinovian.submission1.R.menu.detail_menu
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.db.database
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.presenter.detail.DetailPresenter
import com.example.harfinovian.submission1.utlis.AppSchedulerProvider
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.text_prop_match_detail.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.getStackTraceString
import java.lang.Exception

class DetailActivity : AppCompatActivity(), DetailView {

    private var iFragmentPresenter: DetailPresenter? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String
    private var match: Event? = null
    private lateinit var idHome: String
    private lateinit var idAway: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail)

        setToolbar()

        try{
            idHome = intent.getStringExtra("idHome")
            idAway = intent.getStringExtra("idAway")
            id = intent.getStringExtra("idEvent")

            favoriteState()

            val service = APIRepository.getClient().create(TheSportDBApi::class.java)
            val request = MatchRepositoryImpl(service)
            val scheduler = AppSchedulerProvider()
            iFragmentPresenter = DetailPresenter(this, request, scheduler)
            iFragmentPresenter?.getMatchDetail(id)
            iFragmentPresenter?.showLogo(idHome, home_img)
            iFragmentPresenter?.showLogo(idAway, away_img)
        } catch (e :Exception){
            Log.e("Error", e.getStackTraceString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else if (match != null) addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENT to match?.idEvent,
                        Favorite.TEAM_HOME_ID to match?.idHomeTeam,
                        Favorite.TEAM_AWAY_ID to match?.idAwayTeam,
                        Favorite.HOME_TEAM to match?.strHomeTeam,
                        Favorite.AWAY_TEAM to match?.strAwayTeam,
                        Favorite.SCORE_HOME_TEAM to match?.intHomeScore,
                        Favorite.SCORE_AWAY_TEAM to match?.intAwayScore,
                        Favorite.DATE to match?.dateEvent)
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(ID_EVENT = {id})",
                        "id" to id)
            }
            snackbar(swipeRefresh,"Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    override fun setToolbar() {
        setSupportActionBar(main_toolbar)
        val ab = supportActionBar
        ab?.setTitle("Match Detail")
        ab?.setDisplayShowHomeEnabled(true)
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    override fun bindView(res: Event) {
        match = res

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
        Glide.with(applicationContext).load(url).into(imageView)
    }

    private fun addDynamicView(title: String?, homeProp: String?, awayProp: String?): LinearLayout {
        val linearLayout = View.inflate(this,
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
