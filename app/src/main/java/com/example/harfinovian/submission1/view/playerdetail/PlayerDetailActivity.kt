package com.example.harfinovian.submission1.view.playerdetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.R.layout.activity_player_detail
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.PlayerRepositoryImpl
import com.example.harfinovian.submission1.model.Player
import com.example.harfinovian.submission1.presenter.playerdetail.PlayerDetailPresenter
import com.example.harfinovian.submission1.utlis.AppSchedulerProvider
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.text_props_player.view.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {
    private var iFragmentPresenter: PlayerDetailPresenter? = null
    private var player: Player? = null
    private lateinit var idPlayer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_player_detail)

        idPlayer = intent.getStringExtra("idPlayer")

        val service = APIRepository.getClient().create(TheSportDBApi::class.java)
        val request = PlayerRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        iFragmentPresenter = PlayerDetailPresenter(this, request, scheduler)
        iFragmentPresenter?.getPlayerDetail(idPlayer)

    }

    override fun setToolbar(title: String?) {
        setSupportActionBar(main_toolbar)
        val ab = supportActionBar
        ab?.setTitle(title)
        ab?.setDisplayShowHomeEnabled(true)
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    override fun bindView(res: Player) {
        player = res

        setToolbar(res.strPlayer)

        Glide.with(applicationContext).load(res.strFanart1).into(player_img)

        content_ly.addView(addDynamicView("Weight (Kg)", res.strWeight))
        content_ly.addView(addDynamicView("Height (m)", res.strHeight))
        content_ly.addView(addDynamicView("Position)", res.strPosition))

        overview_txt.text = res.strDescriptionEN
    }

    private fun addDynamicView(props: String?, value: String?): LinearLayout {
        val linearLayout = View.inflate(this,
                R.layout.text_props_player, null) as LinearLayout

        linearLayout.prop_txt.text = props
        linearLayout.value_txt.text = value

        return linearLayout
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
