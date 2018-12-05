package com.example.harfinovian.submission1.view.teamdetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.harfinovian.submission1.R.drawable.ic_add_to_favorites
import com.example.harfinovian.submission1.R.drawable.ic_added_to_favorites
import com.example.harfinovian.submission1.R.id.add_to_favorite
import com.example.harfinovian.submission1.R.layout.*
import com.example.harfinovian.submission1.R.menu.detail_menu
import com.example.harfinovian.submission1.adapter.ViewPagerAdapter
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.db.Favorite
import com.example.harfinovian.submission1.entity.db.FavoriteTeam
import com.example.harfinovian.submission1.entity.db.database
import com.example.harfinovian.submission1.entity.repository.TeamRepositoryImpl
import com.example.harfinovian.submission1.model.Team
import com.example.harfinovian.submission1.presenter.teamdetail.ITeamDetailPresenter
import com.example.harfinovian.submission1.presenter.teamdetail.TeamDetailPresenter
import com.example.harfinovian.submission1.utlis.AppSchedulerProvider
import com.example.harfinovian.submission1.view.player.PlayerFragment
import com.example.harfinovian.submission1.view.team.TeamFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.text_prop_match_detail.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private var iFragmentPresenter: ITeamDetailPresenter? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String
    private var team: Team? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_team_detail)

        setToolbar()

        id = intent.getStringExtra("idTeam")

        favoriteState()

        val service = APIRepository.getClient().create(TheSportDBApi::class.java)
        val request = TeamRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        iFragmentPresenter = TeamDetailPresenter(this, request, scheduler)
        iFragmentPresenter?.getTeamDetail(id)
        iFragmentPresenter?.showLogo(id, team_img)

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.setRefreshing(false)
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
                if (isFavorite) removeFromFavorite() else if (team != null) addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(ID_TEAM = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.ID_TEAM to team?.idTeam,
                        FavoriteTeam.TEAM_NAME to team?.strTeam,
                        FavoriteTeam.TEAM_IMAGE to team?.strTeamLogo)
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(ID_TEAM = {id})",
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
        ab?.setTitle("Team Detail")
        ab?.setDisplayShowHomeEnabled(true)
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    override fun bindView(res: Team) {
        team = res

        team_name_txt.text = res.strTeam
        year_txt.text = res.intFormedYear
        stadium_txt.text = res.strStadium

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val overviewFrag = TeamOverviewFragment()
        val playerFrag = PlayerFragment()
        val args = Bundle()
        args.putParcelable("teams", team)
        overviewFrag.setArguments(args)
        playerFrag.setArguments(args)

        adapter.populateFragment(overviewFrag, "Overview")
        adapter.populateFragment(playerFrag, "Player")
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)

    }

    override fun showLogo(url: String?, imageView : ImageView) {
        Glide.with(applicationContext).load(url).into(imageView)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
