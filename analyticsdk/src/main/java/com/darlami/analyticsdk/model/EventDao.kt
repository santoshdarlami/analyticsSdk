package com.darlami.analyticsdk.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity): Long

    @Query("SELECT * FROM EventEntity WHERE sessionId = :sessionId")
    suspend fun getEventsForSession(sessionId: Long): List<EventEntity>

    @Query("SELECT * FROM EventEntity")
    fun getAllEvents(): Flow<List<EventEntity>>
}