package com.example.harfinovian.submission1.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchRepositoryImplTest {
    @Mock
    lateinit var footballRest: TheSportDBApi

    lateinit var matchRepositoryImpl: MatchRepositoryImpl

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        matchRepositoryImpl = MatchRepositoryImpl(footballRest)
    }

    @Test
    fun getNextMatch() {
        matchRepositoryImpl.getUpcomingMatch("4328")
        Mockito.verify(footballRest).getNextEvent("4328")
    }

    @Test
    fun getLastMatch() {
        matchRepositoryImpl.getPastMatch("4328")
        Mockito.verify(footballRest).getPastEvent("4328")
    }
}