package org.mobwhy.celesmob.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Provides responsive sizing values based on screen dimensions
 */
@Composable
fun responsivePadding(): Dp {
    // This is a simplified approach - in a real app, we'd measure the actual window size
    return if (getWindowWidth() > 800.dp) {
        24.dp
    } else {
        16.dp
    }
}

/**
 * Gets appropriate column count for grids based on screen width
 */
@Composable
fun getVersionGridColumns(): Int {
    val screenWidthDp = getWindowWidth()

    return when {
        screenWidthDp < 600.dp -> 1
        screenWidthDp < 900.dp -> 2
        screenWidthDp < 1200.dp -> 3
        else -> 4
    }
}

/**
 * Gets appropriate content padding based on screen size
 */
@Composable
fun getContentPadding(): Dp {
    val screenWidthDp = getWindowWidth()

    return when {
        screenWidthDp < 600.dp -> 8.dp
        screenWidthDp < 900.dp -> 16.dp
        else -> 24.dp
    }
}

@Composable
private fun getWindowWidth(): Dp {
    // For now, returning a default value since we can't access window dimensions directly in Compose Desktop
    // In a real application, we would use a different approach to get the actual window size
    return 1200.dp
}