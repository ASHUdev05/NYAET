package com.ashudev05.nyaet

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ashudev05.nyaet.screens.Expenses
import com.ashudev05.nyaet.screens.Settings
import com.ashudev05.nyaet.ui.theme.NYAETTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYAETTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "expenses",
                                onClick = { navController.navigate("expenses") },
                                label = {
                                    Text("Expenses")
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.upload_24px),
                                        contentDescription = "Expenses"
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "reports",
                                onClick = { navController.navigate("reports") },
                                label = {
                                    Text("Reports")
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.bar_chart_24px),
                                        contentDescription = "Reports"
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "add",
                                onClick = { navController.navigate("add") },
                                label = {
                                    Text("Add")
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.add_24px),
                                        contentDescription = "Add"
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "settings",
                                onClick = { navController.navigate("settings") },
                                label = {
                                    Text("Settings")
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.settings_outlined_24px),
                                        contentDescription = "Settings"
                                    )
                                }
                            )
                        }
                    },

                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = "expenses") {
                            composable("expenses") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                    color = MaterialTheme.colorScheme.background
                                ) {
                                    Expenses(navController = navController, name = "Expenses")
                                }
                            }
                            composable("reports") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                    color = MaterialTheme.colorScheme.background
                                ) {
                                    Greeting("Reports")
                                }
                            }
                            composable("add") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                    color = MaterialTheme.colorScheme.background
                                ) {
                                    Greeting("Add")
                                }
                            }
                            composable("settings") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                    color = MaterialTheme.colorScheme.background
                                ) {
                                    Settings(navController = navController)
                                }
                            }
                            composable("settings/categories") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                    color = MaterialTheme.colorScheme.background
                                ) {
                                    Greeting("Categories")
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    NYAETTheme {
        Surface {
            Greeting("Android")
        }
    }
}