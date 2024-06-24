package com.darlami.analyticsdk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    val sessionId: Long,
    val eventName: String,
    @PrimaryKey(autoGenerate = true)
    val eventId: Long = 0,
)