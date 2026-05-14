package com.example.expensetracker.repository

import com.example.expensetracker.dao.ExpenseDao
import com.example.expensetracker.model.Expense
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val dao: ExpenseDao) {

    fun getAllExpenses(): Flow<List<Expense>> = dao.getAllExpenses()

    suspend fun insert(expense: Expense) {
        dao.insert(expense)
    }

    suspend fun delete(expense: Expense) {
        dao.delete(expense)
    }


}