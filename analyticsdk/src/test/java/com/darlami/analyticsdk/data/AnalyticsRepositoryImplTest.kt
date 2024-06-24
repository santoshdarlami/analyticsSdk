package com.darlami.analyticsdk.data

import com.darlami.analyticsdk.model.Event
import com.darlami.analyticsdk.model.EventDao
import com.darlami.analyticsdk.model.EventEntity
import com.darlami.analyticsdk.model.ParamDao
import com.darlami.analyticsdk.model.ParamEntity
import com.darlami.analyticsdk.model.SessionDao
import com.darlami.analyticsdk.model.SessionEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AnalyticsRepositoryImplTest {

    @Mock
    private lateinit var sessionDao: SessionDao

    @Mock
    private lateinit var eventDao: EventDao

    @Mock
    private lateinit var paramDao: ParamDao

    private lateinit var analyticsRepository: AnalyticsRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        analyticsRepository = AnalyticsRepositoryImpl(sessionDao, eventDao, paramDao)
    }

    @Test
    fun saveSession() {
        runBlocking {
            val sessionEntity = SessionEntity(0, 1)
            val event = Event(eventName = "testEvent", params = mapOf("key" to "value"))

            `when`(sessionDao.insertSession(sessionEntity)).thenReturn(1L)
            `when`(eventDao.insertEvent(EventEntity(1L, event.eventName))).thenReturn(1L)

            analyticsRepository.saveSession(sessionEntity, listOf(event))

            verify(sessionDao, times(1)).insertSession(sessionEntity)
            verify(eventDao, times(1)).insertEvent(EventEntity(1L, event.eventName))
            verify(paramDao, times(1)).insertParam(ParamEntity(1L, "key", "value"))

        }
    }
}