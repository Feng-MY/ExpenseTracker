package com.example.expensetracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetracker.model.Expense

@Composable
fun ExpenseItem(expense: Expense) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(all = 12.dp),
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1F26)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(12.dp)) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                val amount_2dp = String.format("%.2f", expense.amount)
                Text(expense.category, color = Color.White, fontWeight = FontWeight.Bold)
                Text(
                    "RM ${amount_2dp}",
                    color = if (expense.amount >= 0) Color(0xFF2ECC71) else Color.Red
                )
            }

            Spacer(Modifier.height(4.dp))

            expense.note?.let { Text(it, color = Color.Gray, fontSize = 12.sp) }
            Spacer(Modifier.height(4.dp))

            Text("${expense.expenseDate} ", color = Color.Gray, fontSize = 12.sp)
        }
    }
}