package com.example.fcap.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fcap.viewmodel.SessionStatus
import com.example.fcap.viewmodel.SessionViewModel

@Composable
fun SessionScreen(viewModel: SessionViewModel) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = "Status: ${state.status}",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Focused: ${formatTime(state.elapsedSeconds)} / ${formatTime(state.targetSeconds)}",
            style = MaterialTheme.typography.headlineSmall
        )


        Spacer(modifier = Modifier.height(16.dp))

        when (state.status) {

            SessionStatus.IDLE -> {
                Button(onClick = viewModel::startSession) {
                    Text("Start Focus")
                }
            }

            SessionStatus.FOCUSING -> {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = viewModel::pauseSession) {
                        Text("Pause")
                    }
                    Button(onClick = viewModel::stopSession) {
                        Text("Stop")
                    }
                }
            }

            SessionStatus.PAUSED -> {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = viewModel::startSession) {
                        Text("Resume")
                    }
                    Button(onClick = viewModel::stopSession) {
                        Text("Stop")
                    }
                }
            }

            SessionStatus.ENDED -> {
                Text(
                    text = "Result: ${state.result}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = viewModel::startSession) {
                    Text("Start New Session")
                }
            }
        }
    }

}
private fun formatTime(seconds: Long): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return "%02d:%02d".format(minutes, secs)
}

