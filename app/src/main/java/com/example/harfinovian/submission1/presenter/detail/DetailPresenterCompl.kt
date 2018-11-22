package com.example.harfinovian.submission1.presenter.detail

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.view.detail.DetailView
import com.example.harfinovian.submission1.view.fragment.MatchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenterCompl(private var detailView: DetailView) : IDetailPresenter {

    private val retrofit = APIRepository.getClient()
    private val detail = retrofit.create<TheSportDBApi>(TheSportDBApi::class.java)

    override fun getMatchDetail(idEvent: String?) {
        detail.getEvent(idEvent)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            Log.v("okhttp", data.toString())
                            val res = data.events.get(0)

                            detailView.bindView(res)
                        },
                        { error ->
                            Log.e("Error", error.message)
                        }
                )
    }

    override fun showLogo(param: String?, imageView: ImageView) {
        detail.getTeam(param)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            Log.v("okhttp", data.toString())
                            detailView.showLogo(data.teams.get(0).strTeamLogo, imageView)
                        },
                        { error ->
                            Log.e("Error", error.message)
                        }
                )
    }
}