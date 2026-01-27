package com.example.fcap.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fcap.data.local.SessionEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryItem(
    session: SessionEntity,
    onLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},          // normal click (unused for now)
                onLongClick = onLongClick
            )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Duration: ${session.durationSeconds} sec")
            Text("Result: ${session.result}")
        }
    }
}
