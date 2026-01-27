package com.example.fcap.ui.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fcap.data.local.SessionEntity
import com.example.fcap.ui.components.HistoryItem

@Composable
fun HistoryScreen(
    sessions: List<SessionEntity>,
    onDelete: (SessionEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Session History",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (sessions.isEmpty()) {
            Text("No sessions yet")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(sessions) { session ->
                    HistoryItem(
                        session = session,
                        onLongClick = {
                            onDelete(session)
                        }
                    )
                }
            }
        }
    }
}
