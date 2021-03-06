package com.example.harfinovian.submission1.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailRepositoryImplTest {

    @Mock
    lateinit var footballRest: TheSportDBApi
    lateinit var matchRepositoryImpl: MatchRepositoryImpl

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        matchRepositoryImpl = MatchRepositoryImpl(footballRest)
    }

    @Test
    fun getMatchDetail() {
        matchRepositoryImpl.getEventById("576606")
    }
}