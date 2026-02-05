package org.mobwhy.celesmob.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.mobwhy.celesmob.viewmodel.MainViewModel

@Composable
fun AccountsScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Current account section
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.size(48.dp)
                )
                
                Column {
                    Text(
                        text = "Active Account",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Player123 (Microsoft)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Button(onClick = { /* Switch account */ }) {
                    Text("Switch")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Saved accounts section
        Text(
            text = "Saved Accounts",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.getAccountList()) { account ->
                AccountCard(account = account)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Add account button
        Button(
            onClick = { /* Add new account */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
                Text("Add Account")
            }
        }
    }
}

@Composable
fun AccountCard(account: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Account",
                modifier = Modifier.size(32.dp)
            )
            
            Text(
                text = account,
                style = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            IconButton(onClick = { /* Remove account */ }) {
                Icon(Icons.Default.Delete, contentDescription = "Remove")
            }
        }
    }
}