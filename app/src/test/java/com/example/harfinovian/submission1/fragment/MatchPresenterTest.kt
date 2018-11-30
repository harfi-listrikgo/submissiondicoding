package com.example.harfinovian.submission1.fragment

import com.example.harfinovian.submission1.api.APIRepository
import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.presenter.match.MatchPresenterCompl
import com.example.harfinovian.submission1.view.fragment.MatchView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    lateinit var view: MatchView

    @Mock
    lateinit var apiRepository: APIRepository

    @Mock
    lateinit var gson: Gson

    lateinit var presenter: MatchPresenterCompl

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenterCompl(view)
    }

    @Test
    fun getLeagueAll() {
        val data: List<Event> = mutableListOf()
        val response = Events(data)

        Mockito.`when`(gson.fromJson(apiRepository
                .getClient(TheSportDBApi.),
                LeagueResponse::class.java)
        ).thenReturn(response)

        presenter.getLeagueAll()

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showLeagueList(response)
        Mockito.verify(view).hideLoading()
    }
}