package com.darlami.analyticsdk.data

import com.darlami.analyticsdk.model.Event
import com.darlami.analyticsdk.model.EventEntity
import com.darlami.analyticsdk.model.ParamEntity
import com.darlami.analyticsdk.model.SessionEntity
import kotlinx.coroutines.flow.Flow

interface AnalyticsRepository {
    fun saveSession(sessionEntity: SessionEntity, events: List<Event>)
    fun getAllSessions(): Flow<List<SessionEntity>>
    fun getAllEvents(): Flow<List<EventEntity>>
    fun getAllParams(): Flow<List<ParamEntity>>
}
