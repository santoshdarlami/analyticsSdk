package com.darlami.analyticsdk.model

data class EventWithParams(
    val eventId: Long,
    val sessionId: Long,
    val eventName: String,
    val params: List<ParamEntity>,
)
