package org.mobwhy.celesmob.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.mobwhy.celesmob.viewmodel.MainViewModel
import org.mobwhy.celesmob.utils.getVersionGridColumns

@Composable
fun VersionsScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search and filter bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Search versions...") },
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { /* Filter */ }) {
                Icon(Icons.Default.Search, contentDescription = "Filter")
            }
        }

        // Quick launch section
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Quick Launch",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "1.21.4 (Release)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Button(onClick = { /* Launch version */ }) {
                    Text("Launch")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Version grid
        Text(
            text = "Available Versions",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(getVersionGridColumns()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.getVersionList()) { version ->
                VersionCard(version = version)
            }
        }
    }
}

@Composable
fun VersionCard(version: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = version,
                    style = MaterialTheme.typography.titleMedium
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Details"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { /* Launch */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Launch")
                }

                OutlinedButton(
                    onClick = { /* Install/Download */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Install")
                }
            }
        }
    }
}