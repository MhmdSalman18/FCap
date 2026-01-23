package com.example.fcap.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val durationSeconds: Long,
    val result: String,
    val timestamp: Long
)
