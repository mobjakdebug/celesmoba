package org.mobwhy.celesmob.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainViewModel {
    var appVersion by mutableStateOf("1.0.0")
        private set
    
    private val _versionList = mutableStateListOf<String>()
    private val _accountList = mutableStateListOf<String>()
    
    init {
        // Initialize with sample data
        _versionList.addAll(listOf(
            "1.21.4 (Release)",
            "1.21.3 (Release)", 
            "1.21.1 (Release)",
            "1.20.6 (Release)",
            "1.19.4 (Release)",
            "1.18.2 (Release)",
            "1.17.1 (Release)",
            "1.16.5 (Release)",
            "1.12.2 (Legacy)",
            "1.8.9 (Legacy)"
        ))
        
        _accountList.addAll(listOf(
            "Player123 (Microsoft)",
            "Steve456 (Mojang)",
            "Alex789 (Offline)"
        ))
    }
    
    fun getVersionList(): List<String> = _versionList.toList()
    
    fun getAccountList(): List<String> = _accountList.toList()
    
    fun addAccount(account: String) {
        _accountList.add(account)
    }
    
    fun removeAccount(account: String) {
        _accountList.remove(account)
    }
}