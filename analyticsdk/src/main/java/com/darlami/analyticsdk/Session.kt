package com.darlami.analyticsdk

import com.darlami.analyticsdk.data.AnalyticsRepository
import com.darlami.analyticsdk.model.Event
import com.darlami.analyticsdk.model.SessionEntity

class Session(private val analyticsRepository: AnalyticsRepository) {

    val events = mutableListOf<Event>()
    var startTime: Long = 0
    var endTime: Long = 0

    fun start() {
        startTime = System.currentTimeMillis()
    }

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun stop() {
        endTime = System.currentTimeMillis()
        saveSession()
    }

    private fun saveSession() {
        analyticsRepository.saveSession(SessionEntity(startTime, endTime), events)
    }
}