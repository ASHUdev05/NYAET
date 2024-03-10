package com.ashudev05.nyaet.viewmodels

import androidx.lifecycle.ViewModel
import com.ashudev05.nyaet.models.Recurrence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class AddScreenState(
    val amount: Number,
    val recurrence: Recurrence,
    val date: LocalDate,
    val note: String = "",
    val category: String,
)

class AddViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AddScreenState(
        amount = 0.0,
        recurrence = Recurrence.None,
        date = LocalDate.now(),
        category = ""
    ))
    val uiState: StateFlow<AddScreenState> = _uiState.asStateFlow()

    fun setAmount(amount: Double) {
        _uiState.update { currentState ->
            currentState.copy(amount = amount)
        }
    }

    fun setRecurrence(recurrence: Recurrence) {
        _uiState.update { currentState ->
            currentState.copy(recurrence = recurrence)
        }
    }

    fun setDate(date: LocalDate) {
        _uiState.update { currentState ->
            currentState.copy(date = date)
        }
    }

    fun setNote(note: String) {
        _uiState.update { currentState ->
            currentState.copy(note = note)
        }
    }

    fun setCategory(category: String) {
        _uiState.update { currentState ->
            currentState.copy(category = category)
        }
    }

    fun submitExpense() {
        // TODO: Implement this
    }
}