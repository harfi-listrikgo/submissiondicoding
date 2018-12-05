package com.example.harfinovian.submission1.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.entity.repository.SearchRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchRepositoryImplTest {
    @Mock
    lateinit var footballRest: TheSportDBApi

    lateinit var matchRepositoryImpl: SearchRepositoryImpl

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        matchRepositoryImpl = SearchRepositoryImpl(footballRest)
    }

    @Test
    fun getSearchMatch() {
        matchRepositoryImpl.searchMatches("Arsenal")
        Mockito.verify(footballRest).searchMatches("Arsenal")
    }

    @Test
    fun getSearchTeam() {
        matchRepositoryImpl.searchTeam("Arsenal")
        Mockito.verify(footballRest).getTeamBySearch("Arsenal")
    }
}