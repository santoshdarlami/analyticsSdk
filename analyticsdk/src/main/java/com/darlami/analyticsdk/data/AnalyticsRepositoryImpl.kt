package com.darlami.analyticsdk.data

import com.darlami.analyticsdk.model.Event
import com.darlami.analyticsdk.model.EventDao
import com.darlami.analyticsdk.model.EventEntity
import com.darlami.analyticsdk.model.ParamDao
import com.darlami.analyticsdk.model.ParamEntity
import com.darlami.analyticsdk.model.SessionDao
import com.darlami.analyticsdk.model.SessionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AnalyticsRepositoryImpl(
    private val sessionDao: SessionDao,
    private val eventDao: EventDao,
    private val paramDao: ParamDao,
) : AnalyticsRepository {
    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun saveSession(sessionEntity: SessionEntity, events: List<Event>) {
        repositoryScope.launch {
            try {
                val sessionId = sessionDao.insertSession(sessionEntity)
                saveEvent(sessionId, events)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getAllSessions(): Flow<List<SessionEntity>> {
        return sessionDao.getAllSession()
    }

    override fun getAllEvents(): Flow<List<EventEntity>> {
        return eventDao.getAllEvents()
    }

    override fun getAllParams(): Flow<List<ParamEntity>> {
        return paramDao.getAllParams()
    }

    private suspend fun saveEvent(sessionId: Long, events: List<Event>) {
        events.forEach { event ->
            val eventId = eventDao.insertEvent(EventEntity(sessionId, event.eventName))
            saveParam(eventId, event.params)
        }
    }

    private suspend fun saveParam(eventId: Long, params: Map<String, String>) {
        params.forEach { param ->
            paramDao.insertParam(ParamEntity(eventId, param.key, param.value))
        }
    }
}
