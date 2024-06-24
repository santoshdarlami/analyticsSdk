package com.darlami.analyticsdk.model

data class SessionWithEventsAndParams(
    val sessionId: Long,
    val startTime: Long,
    val endTime: Long,
    val events: List<EventWithParams>
)