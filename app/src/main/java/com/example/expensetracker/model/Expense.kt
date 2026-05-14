package com.example.expensetracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense")
data class Expense (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("amount") var amount: Double,
    @ColumnInfo("category") var category: String,
    @ColumnInfo("expenseDate") var expenseDate: String,
    @ColumnInfo("type") var type: String,
    @ColumnInfo("note") var note: String? = null


)