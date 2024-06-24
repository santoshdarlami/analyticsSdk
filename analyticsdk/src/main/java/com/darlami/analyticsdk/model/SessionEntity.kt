package com.darlami.analyticsdk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SessionEntity(
    val startTime: Long,
    val endTime: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)