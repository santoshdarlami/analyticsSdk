package com.darlami.analyticsdk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParamEntity(
    val eventId: Long,
    val name: String,
    val value: String,
    @PrimaryKey(autoGenerate = true)
    val paramId: Long = 0,
)
