package com.darlami.analyticsdk.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity): Long

    @Query("SELECT * FROM SessionEntity")
    fun getAllSession(): Flow<List<SessionEntity>>

    @Query("SELECT * FROM SessionEntity WHERE id = :sessionId")
    suspend fun getSession(sessionId: Long): SessionEntity?

}