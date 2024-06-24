package com.darlami.analyticsdk

import android.app.Application
import androidx.room.Room
import com.darlami.analyticsdk.data.AnalyticsDatabase
import com.darlami.analyticsdk.data.AnalyticsRepository
import com.darlami.analyticsdk.data.AnalyticsRepositoryImpl
import com.darlami.analyticsdk.model.Event
import com.darlami.analyticsdk.model.EventEntity
import com.darlami.analyticsdk.model.EventType
import com.darlami.analyticsdk.model.ParamEntity
import com.darlami.analyticsdk.model.SessionEntity
import kotlinx.coroutines.flow.Flow

class AndroidAnalyticsSDK(application: Application) {
    var session: Session? = null
    private val database = Room.databaseBuilder(application, AnalyticsDatabase::class.java, "analytics-db").build()
    private val sessionDao = database.sessionDao()
    private val eventDao = database.eventDao()
    private val paramDao = database.paramDao()
    private var analyticsRepository: AnalyticsRepository = AnalyticsRepositoryImpl(sessionDao, eventDao, paramDao)

    init {
        startSession()
    }

    fun startSession() {
        if (session == null) {
            session = Session(analyticsRepository)
            session?.start()
        }
    }

    fun addEvent(eventName: String, vararg params: Pair<String, String>) {
        val event = Event(eventName = eventName, params = mapOf(*params))
        addEvent(event)
    }

    fun addEvent(eventType: EventType, vararg params: Pair<String, String>) {
        val event = Event(eventName = eventType.eventName, params = mapOf(*params))
        addEvent(event)
    }

    fun stopSession() {
        session?.stop()
        session = null
    }

    fun getAllSessions(): Flow<List<SessionEntity>> {
        return analyticsRepository.getAllSessions()
    }

    fun getAllEvents(): Flow<List<EventEntity>> {
        return analyticsRepository.getAllEvents()
    }

    fun getAllParams(): Flow<List<ParamEntity>> {
        return analyticsRepository.getAllParams()
    }

    private fun addEvent(event: Event) {
        session?.addEvent(event)
    }

    companion object {
        @Volatile
        private var instance: AndroidAnalyticsSDK? = null

        fun getInstance(context: Application): AndroidAnalyticsSDK {
            return instance ?: synchronized(this) {
                instance ?: AndroidAnalyticsSDK(context).also { instance = it }
            }
        }
    }
}