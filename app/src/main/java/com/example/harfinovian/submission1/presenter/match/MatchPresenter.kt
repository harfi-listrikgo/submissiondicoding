package com.example.harfinovian.submission1.presenter.match

import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.view.fragment.MatchView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class MatchPresenter(val mView : MatchView,
                     private val matchRepositoryImpl: MatchRepositoryImpl,
                     private val scheduler: SchedulerProvider) : IMatchPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData(param: String?, id: String) {
        mView.showLoading()
        if (param.equals("last")) {
            compositeDisposable.add(matchRepositoryImpl.getPastMatch(id)
                    .observeOn(scheduler.ui())
                    .subscribeOn(scheduler.io())
                    .subscribeWith(object : ResourceSubscriber<Events>(){
                        override fun onComplete() {
                            mView.hideLoading()
                        }

                        override fun onNext(t: Events) {
                            mView.showEventList(t.events)
                        }

                        override fun onError(e: Throwable) {
                            mView.hideLoading()
                        }

                    })
            )
        } else if (param.equals("next")){
            compositeDisposable.add(matchRepositoryImpl.getUpcomingMatch(id)
                    .observeOn(scheduler.ui())
                    .subscribeOn(scheduler.io())
                    .subscribeWith(object : ResourceSubscriber<Events>(){
                        override fun onComplete() {
                            mView.hideLoading()
                        }

                        override fun onNext(t: Events) {
                            mView.showEventList(t.events)
                        }

                        override fun onError(e: Throwable) {
                            mView.hideLoading()
                        }

                    })
            )
        }
    }

    override fun onDestroy(){
        compositeDisposable.dispose()
    }
}