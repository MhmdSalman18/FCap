package com.example.fcap.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onClearClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            if (onClearClick != null) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Clear History"
                    )
                }
            }
        }
    )
}
