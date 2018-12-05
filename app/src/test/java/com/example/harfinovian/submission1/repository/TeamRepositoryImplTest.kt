package com.example.harfinovian.submission1.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.entity.repository.TeamRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamRepositoryImplTest {
    @Mock
    lateinit var footballRest: TheSportDBApi

    lateinit var matchRepositoryImpl: TeamRepositoryImpl

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        matchRepositoryImpl = TeamRepositoryImpl(footballRest)
    }

    @Test
    fun getAllTeam() {
        matchRepositoryImpl.getListTeam("4328")
        Mockito.verify(footballRest).getAllTeam("4328")
    }

    @Test
    fun getTeam() {
        matchRepositoryImpl.getTeam("133604")
        Mockito.verify(footballRest).getTeam("133604")
    }
}