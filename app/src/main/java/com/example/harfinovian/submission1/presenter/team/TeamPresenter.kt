package com.example.harfinovian.submission1.presenter.team

import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.entity.repository.TeamRepositoryImpl
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Teams
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.view.fragment.MatchView
import com.example.harfinovian.submission1.view.team.TeamView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class TeamPresenter(val mView : TeamView,
                    private val teamRepositoryImpl: TeamRepositoryImpl,
                    private val scheduler: SchedulerProvider) : ITeamPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballTeamData(idLeague: String) {
        mView.showLoading()
            compositeDisposable.add(teamRepositoryImpl.getListTeam(idLeague)
                    .observeOn(scheduler.ui())
                    .subscribeOn(scheduler.io())
                    .subscribeWith(object : ResourceSubscriber<Teams>(){
                        override fun onComplete() {
                            mView.hideLoading()
                        }

                        override fun onNext(t: Teams) {
                            mView.showTeamList(t.teams)
                        }

                        override fun onError(e: Throwable) {
                            mView.hideLoading()
                        }

                    })
            )
    }
}