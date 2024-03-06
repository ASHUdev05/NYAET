package com.ashudev05.nyaet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ashudev05.nyaet.components.TableRow
import com.ashudev05.nyaet.components.UnstyledTextField
import com.ashudev05.nyaet.ui.theme.Shapes
import com.ashudev05.nyaet.ui.theme.dividerColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(
    navController: NavController
) {
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
            Column(modifier = Modifier.padding(innerPadding)) {
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
                            value = "Hello",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.End
                            ),
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
                    )
                    Divider(
                        modifier = Modifier.padding(start = 16.dp),
                        thickness = 1.dp,
                        color = dividerColor
                    )
                    TableRow(
                        label = "Date",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                    Divider(
                        modifier = Modifier.padding(start = 16.dp),
                        thickness = 1.dp,
                        color = dividerColor
                    )
                    TableRow(
                        label = "Note",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                    Divider(
                        modifier = Modifier.padding(start = 16.dp),
                        thickness = 1.dp,
                        color = dividerColor
                    )
                    TableRow(
                        label = "Category",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }
        }
    )
}