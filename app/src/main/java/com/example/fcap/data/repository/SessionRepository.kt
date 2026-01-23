package com.example.fcap.data.repository

import android.se.omapi.Session
import com.example.fcap.data.local.SessionDao
import com.example.fcap.data.local.SessionEntity
import com.example.fcap.viewmodel.SessionResult
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

class SessionRepository (
    private val dao: SessionDao
){
    suspend fun saveSession(
        durationSeconds: Long,
        result: SessionResult
    ){
        dao.insertSession(
            SessionEntity(
                durationSeconds = durationSeconds,
                result = result.name,
                timestamp = System.currentTimeMillis()
            )
        )
    }
    fun getSessionHistory(): Flow<List<SessionEntity>>{
        return dao.getAllSessions()
    }

}