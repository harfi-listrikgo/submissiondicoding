package com.example.harfinovian.submission1.presenter.player

import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.entity.repository.PlayerRepositoryImpl
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Players
import com.example.harfinovian.submission1.presenter.match.IMatchPresenter
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.view.player.PlayerFragment
import com.example.harfinovian.submission1.view.player.PlayerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class PlayerPresenter(val mView : PlayerView,
                     private val playerRepositoryImpl: PlayerRepositoryImpl,
                     private val scheduler: SchedulerProvider) : IPlayerPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getPlayerListData(id: String?) {
        mView.showLoading()
        compositeDisposable.add(playerRepositoryImpl.getListPlayer(id)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<Players>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: Players) {
                        mView.showPlayerList(t.player)
                    }

                    override fun onError(e: Throwable) {
                        mView.hideLoading()
                    }

                })
        )
    }

}