package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.expensetracker.database.ExpenseDatabase
import com.example.expensetracker.repository.ExpenseRepository
import com.example.expensetracker.ui.ExpenseApp
import com.example.expensetracker.viewmodel.ExpenseViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = ExpenseDatabase.getDatabase(applicationContext)
        val repo = ExpenseRepository(db.expenseDao())
        val viewModel = ExpenseViewModel(repo)

        setContent {
            ExpenseApp(
                viewModel = viewModel
            )
        }
    }
}