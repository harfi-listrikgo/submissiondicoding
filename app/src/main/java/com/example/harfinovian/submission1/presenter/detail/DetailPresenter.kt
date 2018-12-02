package com.example.harfinovian.submission1.presenter.detail

import android.util.Log
import android.widget.ImageView
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Team
import com.example.harfinovian.submission1.model.Teams
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.view.detail.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class DetailPresenter(private var detailView: DetailView,
                           private val matchRepositoryImpl: MatchRepositoryImpl,
                           private val scheduler: SchedulerProvider) : IDetailPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getMatchDetail(idEvent: String) {
        compositeDisposable.add(matchRepositoryImpl.getEventById(idEvent)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<Events>(){
                    override fun onComplete() {
                    }

                    override fun onNext(t: Events) {
                        detailView.bindView(t.events.get(0))
                    }

                    override fun onError(e: Throwable) {
                        Log.e("DetailErr", e.message)
                    }

                })
        )
    }

    override fun showLogo(param: String, imageView: ImageView) {
        compositeDisposable.add(matchRepositoryImpl.getTeam(param)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<Teams>(){
                    override fun onComplete() {
                    }

                    override fun onNext(t: Teams) {
                        detailView.showLogo(t.teams.get(0).strTeamLogo, imageView)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Error", e.message)
                    }

                })
        )
    }
}