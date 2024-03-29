package com.ashudev05.nyaet.screens

import android.app.DatePickerDialog
import android.content.res.Configuration
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ashudev05.nyaet.components.TableRow
import com.ashudev05.nyaet.components.UnstyledTextField
import com.ashudev05.nyaet.models.Recurrence
import com.ashudev05.nyaet.ui.theme.NYAETTheme
import com.ashudev05.nyaet.ui.theme.Shapes
import com.ashudev05.nyaet.ui.theme.dividerColor
import com.ashudev05.nyaet.viewmodels.AddViewModel
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(
    navController: NavController,
    addViewModel: AddViewModel = viewModel()
) {
    val state by addViewModel.uiState.collectAsState()
    val recurrences = listOf(
        Recurrence.None,
        Recurrence.Daily,
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly,
    )
    val categories = listOf("Food", "Transport", "Entertainment", "Utilities", "Other")

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()
    val mContext = LocalContext.current

    mCalendar.time = Date()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mDate = remember { mutableStateOf("${mDay}/${mMonth+1}/$mYear") }



    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Add",
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clip(Shapes.medium)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    TableRow(
                        label = "Amount",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    ) {
                        UnstyledTextField(
                            value = state.amount,
                            onValueChange = addViewModel::setAmount,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,

                                ),
                            placeholder = { Text("₹ 0.00") },
                            arrangement = Arrangement.End,
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            ),
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(start = 16.dp),
                        thickness = 1.dp,
                        color = dividerColor
                    )
                    TableRow(
                        label = "Recurrence",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    ) {
                        var recurrenceMenuOpened by remember { mutableStateOf(false) }
                        TextButton(onClick = { recurrenceMenuOpened = true }) {
                            Text(text = state.recurrence?.name ?: Recurrence.None.name)
                            DropdownMenu(
                                expanded = recurrenceMenuOpened,
                                onDismissRequest = { recurrenceMenuOpened = false }) {
                                recurrences.forEach { recurrence ->
                                    DropdownMenuItem(
                                        text = { Text(recurrence.name) },
                                        onClick = {
                                            addViewModel.setRecurrence(recurrence)
                                            recurrenceMenuOpened = false
                                        },
                                    )
                                }
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(start = 16.dp),
                        thickness = 1.dp,
                        color = dividerColor
                    )
                    val datePickerShowing = remember { mutableStateOf(false) }
                    TableRow(
                        label = "Date",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    ) {
                        TextButton(onClick = {
                            datePickerShowing.value = true
                        }) {
                            Text(text = mDate.value)
                        }
                        if(datePickerShowing.value){
                            val mDatePickerDialog = DatePickerDialog(
                                mContext,
                                { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                                    mDate.value = "$selectedDay/${selectedMonth+1}/$selectedYear"
                                }, mYear, mMonth, mDay
                            )

                            mDatePickerDialog.datePicker.maxDate = mCalendar.timeInMillis
                            mDatePickerDialog.show()
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(start = 16.dp),
                        thickness = 1.dp,
                        color = dividerColor
                    )
                    TableRow(
                        label = "Note",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    ) {
                        UnstyledTextField(
                            value = state.note,
                            placeholder = { Text("Leave a note") },
                            arrangement = Arrangement.End,
                            onValueChange = addViewModel::setNote,
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right
                            ),
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(start = 16.dp),
                        thickness = 1.dp,
                        color = dividerColor
                    )
                    TableRow(
                        label = "Category",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    ) {
                        var categoriesMenuOpened by remember { mutableStateOf(false) }
                        TextButton(onClick = { categoriesMenuOpened = true }) {
                            Text(text = state.category ?: "Select Category")
                            DropdownMenu(
                                expanded = categoriesMenuOpened,
                                onDismissRequest = { categoriesMenuOpened = false }) {
                                categories.forEach { category ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Surface(modifier = Modifier.size(10.dp), shape = CircleShape, color = MaterialTheme.colorScheme.primary) {}
                                                Text(category, modifier = Modifier.padding(start = 8.dp))
                                            }
                                        },
                                        onClick = {
                                            addViewModel.setCategory(category)
                                            categoriesMenuOpened = false
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
                Button(
                    onClick = addViewModel::submitExpense,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(text = "Submit Expense")
                }
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAdd() {
    NYAETTheme {
        val navController = rememberNavController()
        Add(navController)
    }
}