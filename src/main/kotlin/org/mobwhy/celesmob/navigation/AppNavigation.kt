package org.mobwhy.celesmob.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.mobwhy.celesmob.screens.*
import org.mobwhy.celesmob.viewmodel.MainViewModel
import org.mobwhy.celesmob.animations.PageTransition

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    // For now, always show sidebar in desktop app
    val showSidebar = true

    Row {
        // Sidebar navigation
        NavigationRail(
            modifier = Modifier
                .fillMaxHeight()
                .width(intrinsicSize = IntrinsicSize.Min),
            header = {
                Text(
                    text = "CelesMob",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
        ) {
            NavigationItem(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") },
                onClick = { navController.navigate(Screen.Home) },
                selected = navController.currentScreen == Screen.Home
            )

            NavigationItem(
                icon = { Icon(Icons.Default.List, contentDescription = null) },
                label = { Text("Versions") },
                onClick = { navController.navigate(Screen.Versions) },
                selected = navController.currentScreen == Screen.Versions
            )

            NavigationItem(
                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text("Accounts") },
                onClick = { navController.navigate(Screen.Accounts) },
                selected = navController.currentScreen == Screen.Accounts
            )

            NavigationItem(
                icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                label = { Text("Settings") },
                onClick = { navController.navigate(Screen.Settings) },
                selected = navController.currentScreen == Screen.Settings
            )

            NavigationItem(
                icon = { Icon(Icons.Default.Info, contentDescription = null) },
                label = { Text("About") },
                onClick = { navController.navigate(Screen.About) },
                selected = navController.currentScreen == Screen.About
            )
        }

        // Main content area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            TopAppBar(
                title = { Text("CelesMob Launcher") },
                actions = {
                    IconButton(onClick = { /* Handle notifications */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }

                    IconButton(onClick = { /* Toggle theme */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Toggle theme")
                    }
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                PageTransition {
                    when (navController.currentScreen) {
                        Screen.Home -> HomeScreen(viewModel = viewModel)
                        Screen.Versions -> VersionsScreen(viewModel = viewModel)
                        Screen.Accounts -> AccountsScreen(viewModel = viewModel)
                        Screen.Settings -> SettingsScreen(viewModel = viewModel)
                        Screen.About -> AboutScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun rememberNavController(): NavController {
    return remember { NavController() }
}

class NavController {
    var currentScreen by mutableStateOf(Screen.Home)
        private set

    fun navigate(screen: Screen) {
        currentScreen = screen
    }
}

enum class Screen {
    Home, Versions, Accounts, Settings, About
}

@Composable
fun NavigationItem(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    onClick: () -> Unit,
    selected: Boolean
) {
    NavigationRailItem(
        icon = icon,
        label = label,
        selected = selected,
        onClick = onClick
    )
}