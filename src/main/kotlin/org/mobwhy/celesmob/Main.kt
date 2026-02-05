package org.mobwhy.celesmob

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.mobwhy.celesmob.navigation.AppNavigation
import org.mobwhy.celesmob.theme.CelesMobTheme
import org.mobwhy.celesmob.viewmodel.MainViewModel
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

@Composable
fun CelesMobApp(viewModel: MainViewModel = MainViewModel()) {
    CelesMobTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}

private fun extractNativeLibraries() {
    val tmpDir = File(System.getProperty("java.io.tmpdir"), "celesmob_libs_${System.currentTimeMillis()}")
    tmpDir.mkdirs()
    tmpDir.deleteOnExit()
    
    // List of native libraries to extract for all platforms
    val nativeLibs = listOf(
        // Windows
        "skiko-windows-x64.dll",
        "skiko-windows-x64.dll.sha256",
        // macOS
        "libskiko-macos-x64.dylib",
        "libskiko-macos-x64.dylib.sha256",
        "libskiko-macos-arm64.dylib",
        "libskiko-macos-arm64.dylib.sha256",
        // Linux
        "libskiko-linux-x64.so",
        "libskiko-linux-x64.so.sha256"
    )
    
    try {
        val classLoader = Thread.currentThread().contextClassLoader
        for (libName in nativeLibs) {
            val resourcePath = "/org/jetbrains/skiko/$libName"
            val resourceUrl = classLoader.getResourceAsStream(resourcePath)
            if (resourceUrl != null) {
                val targetFile = File(tmpDir, libName)
                Files.copy(resourceUrl, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
                targetFile.setExecutable(true)
            }
        }
        
        // Add the temp directory to the library path
        val currentPath = System.getProperty("java.library.path", "")
        val newPath = tmpDir.absolutePath + File.pathSeparator + currentPath
        System.setProperty("java.library.path", newPath)
    } catch (e: Exception) {
        System.err.println("Warning: Failed to extract native libraries: ${e.message}")
    }
}

fun main() {
    extractNativeLibraries()
    androidx.compose.ui.window.application {
        CelesMobApp()
    }
}