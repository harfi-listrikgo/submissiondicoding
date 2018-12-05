package com.example.harfinovian.submission1.presenter.searchmatch

import com.example.harfinovian.submission1.entity.repository.SearchRepositoryImpl
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.view.searchmatch.ISearchView
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class SearchPresenter(val mView: ISearchView,
                      val searchRepositoryImpl: SearchRepositoryImpl,
                      val schedulerProvider: SchedulerProvider): ISearchPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun searchMatch(query: String?) {
        mView.showLoading()
        compositeDisposable.add(searchRepositoryImpl.searchMatches(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe {
                    mView.hideLoading()
                    mView.displayMatch(it.event)
                })
    }

    override fun searchTeam(query: String?) {
        mView.showLoading()
        compositeDisposable.add(searchRepositoryImpl.searchTeam(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe {
                    mView.hideLoading()
                    mView.displayTeam(it.teams ?: Collections.emptyList())
                })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}