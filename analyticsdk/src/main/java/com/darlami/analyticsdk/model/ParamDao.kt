package com.darlami.analyticsdk.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ParamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParam(paramEntity: ParamEntity)

    @Query("SELECT * FROM ParamEntity WHERE eventId == :eventId")
    suspend fun getParamsForEventId(eventId: Long): List<ParamEntity>

    @Query("SELECT * FROM ParamEntity")
    fun getAllParams(): Flow<List<ParamEntity>>

}