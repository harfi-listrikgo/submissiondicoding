package com.example.harfinovian.submission1.feature

import com.example.harfinovian.submission1.entity.repository.SearchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Team
import com.example.harfinovian.submission1.model.Teams
import com.example.harfinovian.submission1.presenter.searchmatch.SearchPresenter
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.utlis.TestSchedulerProvider
import com.example.harfinovian.submission1.view.searchmatch.ISearchView
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchTeamPresenterTest {
    @Mock
    lateinit var mView: ISearchView

    @Mock
    lateinit var matchRepositoryImpl: SearchRepositoryImpl

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: SearchPresenter

    lateinit var teams : Teams

    lateinit var footballSearch: Flowable<Teams>

    private val team = mutableListOf<Team>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        teams = Teams(team)
        footballSearch = Flowable.just(teams)
        mPresenter = SearchPresenter(mView, matchRepositoryImpl, scheduler)
        Mockito.`when`(matchRepositoryImpl.searchTeam("Arsenal")).thenReturn(footballSearch)
    }

    @Test
    fun getFootballSearchData() {
        mPresenter.searchTeam("Arsenal")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).displayTeam(team)
        Mockito.verify(mView).hideLoading()
    }

}