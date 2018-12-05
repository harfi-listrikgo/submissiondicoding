package com.example.harfinovian.submission1.presenter.searchmatch

import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.view.searchmatch.SearchMatchView
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class SearchPresenter(val mView: SearchMatchView,
                      val matchRepositoryImpl: MatchRepositoryImpl,
                      val schedulerProvider: SchedulerProvider): ISearchPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun searchMatch(query: String?) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.searchMatches(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe {
                    mView.hideLoading()
                    mView.displayMatch(it.events ?: Collections.emptyList())
                })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}