package com.darlami.analyticsdk

import com.darlami.analyticsdk.data.AnalyticsRepository
import com.darlami.analyticsdk.model.Event
import com.darlami.analyticsdk.model.SessionEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class SessionTest {
    private lateinit var analyticsRepository: AnalyticsRepository
    private lateinit var session: Session


    @Before
    fun setup() {
        analyticsRepository = mock(AnalyticsRepository::class.java)
        session = Session(analyticsRepository)
    }

    @Test
    fun start() {
        session.start()
        assert(session.startTime > 0)
    }

    @Test
    fun addEvent() {
        val event = Event("test_event", mapOf("key" to "value"))
        session.addEvent(event)
        assertEquals(1, session.events.size)
        assertEquals("test_event", session.events[0].eventName)
        assertEquals("value", session.events[0].params["key"])
    }

    @Test
    fun stop() {
        session.start()
        Thread.sleep(100) // Simulate some delay
        session.stop()
        assertTrue(session.endTime > session.startTime)
    }

    @Test
    fun testSessionStopWithEvents() {
        session.start()
        val event1 = Event("event1", mapOf("key1" to "value1"))
        val event2 = Event("event2", mapOf("key2" to "value2"))
        session.addEvent(event1)
        session.addEvent(event2)
        session.stop()

        verify(analyticsRepository, times(1)).saveSession(SessionEntity(session.startTime, session.endTime), session.events)
    }
}