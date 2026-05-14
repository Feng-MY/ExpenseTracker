package com.example.expensetracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.expensetracker.model.Expense
import com.example.expensetracker.viewmodel.ExpenseViewModel

@Composable
fun AddExpenseScreen(
    viewModel: ExpenseViewModel,
    navController: NavController,
    ) {
    val dialogMessage by viewModel.dialogMessage.collectAsState()
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    val tabs = listOf("Expense", "Income")
    var selectedTab by remember { mutableStateOf("Expense") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1115))
            .padding(16.dp)
    ) {
        TopBackTitleBar(navController, "Add Transaction")

        TabRow(
            contentColor = Color.Black,
            containerColor = Color.Black,
            selectedTabIndex = tabs.indexOf(selectedTab)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == title,
                    onClick = { selectedTab = title },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White,
                    text = { Text(title) },
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .background(
                            if (selectedTab == title) Color(0xFF2ECC71)
                            else Color.Black
                        )
                )
            }
        }

        OutlinedTextField(
            value = amount,
            onValueChange = { it ->
                if (it.matches(Regex("^\\d*\\.?\\d*$"))) {
                    amount = it
                }
            },


            label = { Text("Amount")},
            modifier = Modifier
                .padding(
                    top = 20.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Green,
                unfocusedTextColor = Color.Green,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Green,
                cursorColor = Color.Green,
                unfocusedLabelColor = Color.LightGray,
                focusedLabelColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier
                .padding(
                    top = 20.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 0.dp
                    )
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Green,
                unfocusedTextColor = Color.Green,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Green,
                cursorColor = Color.Green,
                unfocusedLabelColor = Color.LightGray,
                focusedLabelColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )

        )

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note(Optional)") },
            modifier = Modifier
                .padding(
                    top = 20.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
                .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Green,
                unfocusedTextColor = Color.Green,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Green,
                cursorColor = Color.Green,
                unfocusedLabelColor = Color.LightGray,
                focusedLabelColor = Color.Green
            )

        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                println("Expense".equals(viewModel.EXPENSE))
                if(viewModel.checkAddExpense(amount, category)){
                    viewModel.addExpense(
                        Expense(
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            category = category,
                            expenseDate = viewModel.getTodayDate(),
                            type = selectedTab,
                            note = note
                        )
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .padding(
                    top = 20.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2ECC71))
        ) {
            Text("Save Expense")
        }

        if (dialogMessage != null) {

            AlertDialog(
                onDismissRequest = { viewModel.clearDialog() },

                confirmButton = {
                    TextButton(onClick = { viewModel.clearDialog() }) {
                        Text("OK")
                    }
                },

                title = {
                    Text("Error")
                },

                text = {
                    Text(dialogMessage!!)
                }
            )
        }

    }
}

