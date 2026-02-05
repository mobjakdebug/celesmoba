package org.mobwhy.celesmob.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.mobwhy.celesmob.viewmodel.MainViewModel

@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // General settings section
            Text(
                text = "General",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            SettingsCard {
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "Language",
                    subtitle = "English",
                    onClick = { /* Change language */ }
                )

                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "Theme",
                    subtitle = "Dark",
                    onClick = { /* Change theme */ }
                )

                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "UI Scaling",
                    subtitle = "100%",
                    onClick = { /* Change scaling */ }
                )
            }
        }

        item {
            // Game settings section
            Text(
                text = "Game",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            SettingsCard {
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "RAM Allocation",
                    subtitle = "4 GB",
                    onClick = { /* Change RAM */ }
                )

                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "Game Directory",
                    subtitle = "~/.minecraft",
                    onClick = { /* Change directory */ }
                )

                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "JVM Arguments",
                    subtitle = "Custom arguments",
                    onClick = { /* Edit JVM args */ }
                )
            }
        }

        item {
            // Add-ons section
            Text(
                text = "Add-ons",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            SettingsCard {
                SwitchSettingsItem(
                    icon = Icons.Default.Info,
                    title = "Weave Support",
                    enabled = false,
                    onCheckedChange = { /* Toggle Weave */ }
                )

                SwitchSettingsItem(
                    icon = Icons.Default.Info,
                    title = "Java Agent Support",
                    enabled = false,
                    onCheckedChange = { /* Toggle Java Agent */ }
                )
            }
        }

        item {
            // Network settings section
            Text(
                text = "Network",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            SettingsCard {
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "API Address",
                    subtitle = "https://api.lunarclientprod.com",
                    onClick = { /* Change API */ }
                )

                SwitchSettingsItem(
                    icon = Icons.Default.Info,
                    title = "Use Proxy",
                    enabled = false,
                    onCheckedChange = { /* Toggle proxy */ }
                )
            }
        }

        item {
            // Advanced settings section
            Text(
                text = "Advanced",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            SettingsCard {
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "Diagnostic Report",
                    subtitle = "Generate diagnostic information",
                    onClick = { /* Generate report */ }
                )

                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "Reset Settings",
                    subtitle = "Restore all settings to defaults",
                    onClick = { /* Reset settings */ }
                )
            }
        }
    }
}

@Composable
fun SettingsCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            content()
        }
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle) },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        modifier = Modifier.clickable { onClick() }
    )
}

@Composable
fun SwitchSettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    enabled: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        trailingContent = {
            Switch(
                checked = enabled,
                onCheckedChange = onCheckedChange
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}