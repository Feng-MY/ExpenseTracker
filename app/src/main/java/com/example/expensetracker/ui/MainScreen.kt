package com.example.expensetracker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.navigation.Screen
import com.example.expensetracker.viewmodel.ExpenseViewModel

@Composable
fun ExpenseApp(viewModel: ExpenseViewModel) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            ExpenseListScreen(viewModel, navController)
        }

        composable(Screen.Add.route) {
            AddExpenseScreen(viewModel, navController)
        }
    }
}