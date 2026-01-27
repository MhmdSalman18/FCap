package com.example.fcap.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fcap.data.local.SessionEntity
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryItem(session: SessionEntity) {

    Card {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text("Duration: ${session.durationSeconds}s")
            Text("Result: ${session.result}")
            Text(
                text = formatDate(session.timestamp),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

private fun formatDate(time: Long): String {
    return SimpleDateFormat(
        "dd MMM yyyy, HH:mm",
        Locale.getDefault()
    ).format(Date(time))
}
