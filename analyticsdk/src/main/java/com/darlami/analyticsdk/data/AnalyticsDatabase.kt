package com.darlami.analyticsdk.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darlami.analyticsdk.model.EventDao
import com.darlami.analyticsdk.model.EventEntity
import com.darlami.analyticsdk.model.ParamDao
import com.darlami.analyticsdk.model.ParamEntity
import com.darlami.analyticsdk.model.SessionDao
import com.darlami.analyticsdk.model.SessionEntity

@Database(entities = [SessionEntity::class, EventEntity::class, ParamEntity::class], version = 1)
abstract class AnalyticsDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun eventDao(): EventDao
    abstract fun paramDao(): ParamDao
}