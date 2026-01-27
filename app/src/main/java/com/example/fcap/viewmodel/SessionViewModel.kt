package com.example.fcap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fcap.data.repository.SessionRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class SessionViewModel(
    private val repository: SessionRepository
) : ViewModel() {


    private val _state = MutableStateFlow(SessionState())
    val state: StateFlow<SessionState> = _state
    val history = repository.getSessionHistory()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    private var timerJob: Job? = null

    fun startSession() {
        _state.value = _state.value.copy(
            status = SessionStatus.FOCUSING,
            result = SessionResult.NONE
        )
        startTimer()
    }

    fun pauseSession() {
        stopTimer()
        _state.value = _state.value.copy(status = SessionStatus.PAUSED)
    }

    fun stopSession() {
        stopTimer()
        _state.value = _state.value.copy(
            status = SessionStatus.ENDED,
            result = SessionResult.FAILED
        )
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1_000)

                val newElapsed = _state.value.elapsedSeconds + 1
                val target = _state.value.targetSeconds

                if (newElapsed >= target) {
                    _state.value = _state.value.copy(
                        elapsedSeconds = target,
                        status = SessionStatus.ENDED,
                        result = SessionResult.COMPLETED
                    )

                    repository.saveSession(
                        durationSeconds = target,
                        result = SessionResult.COMPLETED
                    )

                    stopTimer()
                }

                else {
                    _state.value = _state.value.copy(
                        elapsedSeconds = newElapsed
                    )
                }
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }
}

