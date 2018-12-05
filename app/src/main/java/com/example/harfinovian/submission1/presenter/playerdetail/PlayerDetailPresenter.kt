package com.example.harfinovian.submission1.presenter.playerdetail

import android.util.Log
import android.widget.ImageView
import com.example.harfinovian.submission1.entity.repository.PlayerRepositoryImpl
import com.example.harfinovian.submission1.model.Players
import com.example.harfinovian.submission1.model.Teams
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.view.playerdetail.PlayerDetailView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class PlayerDetailPresenter(private var detailView: PlayerDetailView,
                            private val playerRepositoryImpl: PlayerRepositoryImpl,
                            private val scheduler: SchedulerProvider) : IPlayerDetailPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getPlayerDetail(id: String) {
        compositeDisposable.add(playerRepositoryImpl.getPlayer(id)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<Players>(){
                    override fun onComplete() {
                    }

                    override fun onNext(t: Players) {
                        detailView.bindView(t.players[0])
                    }

                    override fun onError(e: Throwable) {
                        Log.e("DetailErr", e.message)
                    }

                })
        )
    }

}