package com.example.fcap.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.fcap.data.local.AppDatabase
import com.example.fcap.data.repository.SessionRepository
import com.example.fcap.navigation.NavGraph
import com.example.fcap.viewmodel.SessionViewModel
import com.example.fcap.viewmodel.SessionViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        val repository = SessionRepository(db.sessionDao())
        val factory = SessionViewModelFactory(repository)

        setContent {
            val vm: SessionViewModel = viewModel(factory = factory)
            MainScreen(viewModel = vm)

        }

    }
}
