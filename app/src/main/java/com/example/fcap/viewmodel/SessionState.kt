package com.example.fcap.viewmodel

enum class SessionStatus{
    IDLE,
    FOCUSING,
    PAUSED,
    ENDED
}
enum class SessionResult{
    NONE,
    COMPLETED,
    FAILED,

}
data class SessionState (
    val status: SessionStatus = SessionStatus.IDLE,
    val elapsedSeconds: Long = 0L,
    val targetSeconds: Long = 6L,
    val result: SessionResult = SessionResult.NONE,
    val isRunning: Boolean = false,
    val formattedTime: String = "00:00",
    val todayMinutes: Int = 0
)