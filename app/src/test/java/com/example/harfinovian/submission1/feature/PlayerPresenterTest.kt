package com.example.harfinovian.submission1.feature

import com.example.harfinovian.submission1.entity.repository.PlayerRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Player
import com.example.harfinovian.submission1.model.Players
import com.example.harfinovian.submission1.presenter.player.PlayerPresenter
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.utlis.TestSchedulerProvider
import com.example.harfinovian.submission1.view.player.PlayerView
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerPresenterTest {
    @Mock
    lateinit var mView: PlayerView

    @Mock
    lateinit var matchRepositoryImpl: PlayerRepositoryImpl

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: PlayerPresenter

    lateinit var match : Players

    lateinit var footballPlayer: Flowable<Players>

    private val player = mutableListOf<Player>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        match = Players(player)
        footballPlayer = Flowable.just(match)
        mPresenter = PlayerPresenter(mView, matchRepositoryImpl, scheduler)
        Mockito.`when`(matchRepositoryImpl.getListPlayer("133604")).thenReturn(footballPlayer)
    }

    @Test
    fun getFootballPlayerData() {
        mPresenter.getPlayerListData("133604")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).showPlayerList(player)
        Mockito.verify(mView).hideLoading()
    }

}