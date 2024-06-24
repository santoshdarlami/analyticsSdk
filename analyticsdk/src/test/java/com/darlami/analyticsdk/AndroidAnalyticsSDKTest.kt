package com.darlami.analyticsdk

import android.app.Application
import com.darlami.analyticsdk.data.AnalyticsRepository
import com.darlami.analyticsdk.model.Event
import com.darlami.analyticsdk.model.EventType
import com.darlami.analyticsdk.model.SessionEntity
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class AndroidAnalyticsSDKTest {
    @Mock
    private lateinit var application: Application


    private lateinit var androidAnalyticsSDK: AndroidAnalyticsSDK

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        androidAnalyticsSDK = AndroidAnalyticsSDK(application)
    }

    @Test
    fun startSession_startsNewSession() {
        androidAnalyticsSDK.startSession()
        assertNotNull(androidAnalyticsSDK.session)
    }

    @Test
    fun addEvent_addsEventToSession() {
        androidAnalyticsSDK.startSession()
        val event = Event("testEvent", mapOf("key" to "value"))
        androidAnalyticsSDK.addEvent(event.eventName, "key" to "value")
        assertEquals(androidAnalyticsSDK.session!!.events, listOf(event))
    }

    @Test
    fun addEvent_withEventType_addsEventToSession() {
        androidAnalyticsSDK.startSession()
        val event = Event(EventType.PURCHASE.eventName, mapOf("key" to "value"))
        androidAnalyticsSDK.addEvent(EventType.PURCHASE.eventName, "key" to "value")
        assertEquals(androidAnalyticsSDK.session!!.events, listOf(event))
    }

    @Test
    fun stopSession_stopsCurrentSession() {
        androidAnalyticsSDK.startSession()
        androidAnalyticsSDK.stopSession()
        assertNull(androidAnalyticsSDK.session)
    }
}
