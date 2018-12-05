package com.example.harfinovian.submission1.feature

import com.example.harfinovian.submission1.entity.repository.SearchRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
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

class SearchMatchPresenterTest {
    @Mock
    lateinit var mView: ISearchView

    @Mock
    lateinit var matchRepositoryImpl: SearchRepositoryImpl

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: SearchPresenter

    lateinit var match : Events

    lateinit var footballSearch: Flowable<Events>

    private val event = mutableListOf<Event>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        match = Events(event)
        footballSearch = Flowable.just(match)
        mPresenter = SearchPresenter(mView, matchRepositoryImpl, scheduler)
        Mockito.`when`(matchRepositoryImpl.searchMatches("Arsenal")).thenReturn(footballSearch)
    }

    @Test
    fun getFootballSearchData() {
        mPresenter.searchMatch("Arsenal")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).displayMatch(event)
        Mockito.verify(mView).hideLoading()
    }

}