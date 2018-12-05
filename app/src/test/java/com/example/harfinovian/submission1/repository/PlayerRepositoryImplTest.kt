package com.example.harfinovian.submission1.repository

import com.example.harfinovian.submission1.api.TheSportDBApi
import com.example.harfinovian.submission1.entity.repository.MatchRepositoryImpl
import com.example.harfinovian.submission1.entity.repository.PlayerRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerRepositoryImplTest {
    @Mock
    lateinit var footballRest: TheSportDBApi

    lateinit var matchRepositoryImpl: PlayerRepositoryImpl

    @Before
    fun setupEnv() {
        MockitoAnnotations.initMocks(this)
        matchRepositoryImpl = PlayerRepositoryImpl(footballRest)
    }

    @Test
    fun getPlayer() {
        matchRepositoryImpl.getListPlayer("133604")
        Mockito.verify(footballRest).getAllPlayers("133604")
    }

    @Test
    fun getLastMatch() {
        matchRepositoryImpl.getPlayer("34145411")
        Mockito.verify(footballRest).getPlayerDetail("34145411")
    }
}