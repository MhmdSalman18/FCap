package com.example.fcap.ui.session

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fcap.viewmodel.SessionViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SessionScreen(
    viewModel: SessionViewModel,
    onNavigateToHistory: () -> Unit
) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // STATUS
        Text(
            text = state.status.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // TIMER
        Text(
            text = state.formattedTime,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // START / STOP BUTTON
        Button(
            onClick = {
                if (state.isRunning) {
                    viewModel.stopSession()
                } else {
                    viewModel.startSession()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (state.isRunning) "Stop Session" else "Start Focus Session"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // STATS CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${state.todayMinutes} min focused",
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // HISTORY BUTTON
        TextButton(onClick = onNavigateToHistory) {
            Text("View Session History")
        }
    }
}
