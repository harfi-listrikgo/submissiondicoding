package com.example.harfinovian.submission1.feature

import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.presenter.match.TeamPresenter
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.utlis.TestSchedulerProvider
import com.example.harfinovian.submission1.view.fragment.MatchView
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    lateinit var mView: MatchView

    @Mock
    lateinit var matchRepositoryImpl: MatchRepositoryImpl

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: TeamPresenter

    lateinit var match : Events

    lateinit var footballMatch: Flowable<Events>

    private val event = mutableListOf<Event>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        match = Events(event)
        footballMatch = Flowable.just(match)
        mPresenter = TeamPresenter(mView, matchRepositoryImpl, scheduler)
        Mockito.`when`(matchRepositoryImpl.getUpcomingMatch()).thenReturn(footballMatch)
    }

    @Test
    fun getFootballMatchData() {
        mPresenter.getFootballMatchData("next")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).showEventList(event)
        Mockito.verify(mView).hideLoading()
    }

}