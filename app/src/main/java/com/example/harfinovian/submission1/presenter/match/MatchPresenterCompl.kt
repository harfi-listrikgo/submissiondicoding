package com.example.harfinovian.submission1.presenter.match

import android.util.Log
import android.view.View
import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.db.Favorite
import com.example.harfinovian.submission1.db.database
import com.example.harfinovian.submission1.view.fragment.MatchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MatchPresenterCompl(internal var matchView: MatchView) : IMatchPresenter {

    private val retrofit = APIRepository.getClient()
    private val match = retrofit.create<TheSportDBApi>(TheSportDBApi::class.java)

    override fun getAllItemList(view: View, param: String?) {
        matchView.showLoading()
        if (param.equals("last")) {
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
        } else if (param.equals("next")){
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
        } else if (param.equals("fav")){
            view.context?.database?.use {
                val result = select(Favorite.TABLE_FAVORITE)
                val favorite = result.parseList(classParser<Favorite>())
                matchView.showFavoriteList(favorite, view)
            }
        }
    }

}