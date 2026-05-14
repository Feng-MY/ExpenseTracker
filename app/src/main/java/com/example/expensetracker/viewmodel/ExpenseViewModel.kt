package com.example.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.Expense
import com.example.expensetracker.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ExpenseViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    private val _balance = MutableStateFlow(0.0)
    val balance: StateFlow<Double> = _balance

    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage = _dialogMessage.asStateFlow()
    val EXPENSE = "Expense"
    val INCOME = "Income"

    fun clearDialog() {
        _dialogMessage.value = null
    }

    init {
        observeExpenses()
    }

    fun getTodayDate(): String{
        val today = LocalDate.now()

        val formatter = DateTimeFormatter.ofPattern("dd MM yyyy")

        val formattedDate = today.format(formatter)

        return formattedDate;
    }

    private fun observeExpenses() {
        viewModelScope.launch {
            repository.getAllExpenses()
                .collect { list ->

                    _expenses.value = list
                    _balance.value = list.sumOf { it.amount }
                }
        }
    }

    fun addExpense(expense: Expense) {
        if(expense.type.equals(EXPENSE)){
            expense.amount = -expense.amount
            println("inside if statemtn")
        }
        println("bababoy")
        println(expense.amount)
        println(expense)
        viewModelScope.launch {
            repository.insert(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.delete(expense)
        }
    }

    fun checkAddExpense(amount: String, category: String): Boolean{
        if(amount.isEmpty()){
            _dialogMessage.value = "Amount cannot be empty"
            return false
        }else {
            val amountValue = amount.toDoubleOrNull()
            if (amountValue == null || (amountValue != null && amountValue <= 0.00)){
                _dialogMessage.value = "Amount must greater than 0.00"
                return false
            }
        }

        if(category.isEmpty()){
            _dialogMessage.value = "Category cannot be empty"
            return false
        }

        return true
    }
}