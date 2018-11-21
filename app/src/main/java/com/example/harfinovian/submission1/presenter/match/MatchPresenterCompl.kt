package com.example.harfinovian.submission1.presenter.match

import android.util.Log
import android.view.View
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.view.fragment.MatchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchPresenterCompl(internal var matchView: MatchView) : IMatchPresenter {

    override fun getAllItemList(view: View, param: String) {
        matchView.showLoading()
        if (param != "last") {
            val retrofit = APIRepository.getClient()
            val match = retrofit.create<TheSportDBApi>(TheSportDBApi::class.java)

            match.getNextEvent()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { data ->
                                Log.v("okhttp", data.toString())
                                matchView.showEventList(data, view)
                            },
                            { error ->
                                Log.e("Error", error.message)
                            }
                    )
        } else {
            val retrofit = APIRepository.getClient()
            val match = retrofit.create<TheSportDBApi>(TheSportDBApi::class.java)
            match.getPastEvent()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { data ->
                                Log.v("okhttp", data.toString())
                                matchView.showEventList(data, view)
                            },
                            { error ->
                                Log.e("Error", error.message)
                            }
                    )
        }
    }

}