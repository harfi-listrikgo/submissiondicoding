package com.example.harfinovian.submission1.presenter.teamdetail

import android.util.Log
import android.widget.ImageView
import com.example.harfinovian.submission1.entity.repository.TeamRepositoryImpl
import com.example.harfinovian.submission1.model.Teams
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.view.teamdetail.TeamDetailView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class TeamDetailPresenter(private var detailView: TeamDetailView,
                          private val teamRepositoryImpl: TeamRepositoryImpl,
                          private val scheduler: SchedulerProvider) : ITeamDetailPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getTeamDetail(idTeam: String) {
        compositeDisposable.add(teamRepositoryImpl.getTeam(idTeam)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<Teams>(){
                    override fun onComplete() {
                    }

                    override fun onNext(t: Teams) {
                        detailView.bindView(t.teams[0])
                    }

                    override fun onError(e: Throwable) {
                        Log.e("DetailErr", e.message)
                    }

                })
        )
    }

    override fun showLogo(param: String, imageView: ImageView) {
        compositeDisposable.add(teamRepositoryImpl.getTeam(param)
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