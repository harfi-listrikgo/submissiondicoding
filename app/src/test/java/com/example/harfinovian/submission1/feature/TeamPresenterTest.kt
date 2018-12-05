package com.example.harfinovian.submission1.feature

import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.entity.repository.TeamRepositoryImpl
import com.example.harfinovian.submission1.model.Event
import com.example.harfinovian.submission1.model.Events
import com.example.harfinovian.submission1.model.Team
import com.example.harfinovian.submission1.model.Teams
import com.example.harfinovian.submission1.presenter.match.MatchPresenter
import com.example.harfinovian.submission1.presenter.team.TeamPresenter
import com.example.harfinovian.submission1.utlis.SchedulerProvider
import com.example.harfinovian.submission1.utlis.TestSchedulerProvider
import com.example.harfinovian.submission1.view.fragment.MatchView
import com.example.harfinovian.submission1.view.team.TeamView
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
    @Mock
    lateinit var mView: TeamView

    @Mock
    lateinit var matchRepositoryImpl: TeamRepositoryImpl

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: TeamPresenter

    lateinit var teams : Teams

    lateinit var footballMatch: Flowable<Teams>

    private val team = mutableListOf<Team>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        teams = Teams(team)
        footballMatch = Flowable.just(teams)
        mPresenter = TeamPresenter(mView, matchRepositoryImpl, scheduler)
        Mockito.`when`(matchRepositoryImpl.getListTeam("4328")).thenReturn(footballMatch)
    }

    @Test
    fun getFootballMatchData() {
        mPresenter.getFootballTeamData("4328")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).showTeamList(team)
        Mockito.verify(mView).hideLoading()
    }

}