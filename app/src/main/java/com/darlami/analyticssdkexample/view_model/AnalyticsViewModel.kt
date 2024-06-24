package com.darlami.analyticssdkexample.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.darlami.analyticsdk.AndroidAnalyticsSDK
import com.darlami.analyticsdk.model.EventEntity
import com.darlami.analyticsdk.model.EventWithParams
import com.darlami.analyticsdk.model.ParamEntity
import com.darlami.analyticsdk.model.SessionEntity
import com.darlami.analyticsdk.model.SessionWithEventsAndParams
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AnalyticsViewModel(application: Application) : AndroidViewModel(application) {

    private val analyticsSDK = AndroidAnalyticsSDK.getInstance(application)

    private val _sessionFlow: Flow<List<SessionEntity>> = analyticsSDK.getAllSessions()
    private val _eventFlow: Flow<List<EventEntity>> = analyticsSDK.getAllEvents()
    private val _paramFlow: Flow<List<ParamEntity>> = analyticsSDK.getAllParams()

    private val _combinedFlow: MutableStateFlow<List<SessionWithEventsAndParams>> = MutableStateFlow(emptyList())
    val combinedFlow: StateFlow<List<SessionWithEventsAndParams>> = _combinedFlow

    init {
        combineFlows()
    }

    private fun combineFlows() {
        viewModelScope.launch {
            combine(_sessionFlow, _eventFlow, _paramFlow) { sessions, events, params ->
                val eventMap = events.groupBy { it.sessionId }
                val paramMap = params.groupBy { it.eventId }

                sessions.map { session ->
                    val sessionEvents = eventMap[session.id]?.map { event ->
                        EventWithParams(
                            eventId = event.eventId,
                            sessionId = event.sessionId,
                            eventName = event.eventName,
                            params = paramMap[event.eventId] ?: emptyList()
                        )
                    } ?: emptyList()

                    SessionWithEventsAndParams(
                        sessionId = session.id,
                        startTime = session.startTime,
                        endTime = session.endTime,
                        events = sessionEvents
                    )
                }
            }.collectLatest { combinedData ->
                _combinedFlow.value = combinedData
            }
        }
    }
}