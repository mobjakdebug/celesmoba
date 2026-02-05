package org.mobwhy.celesmob.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// CelesMob brand colors
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Extended color palette for CelesMob
val CelesMobBlue = Color(0xFF2B6CB0)
val CelesMobDarkBlue = Color(0xFF1A3F66)
val CelesMobLightBlue = Color(0xFF63B3ED)
val CelesMobAccent = Color(0xFF4FD1C7)
val CelesMobSuccess = Color(0xFF48BB78)
val CelesMobWarning = Color(0xFFF6AD55)
val CelesMobError = Color(0xFFE53E3E)

@Composable
fun CelesMobTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Disable dynamic colors for desktop
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme()
        else -> lightScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}

@Composable
private fun lightScheme(): ColorScheme {
    return lightColorScheme(
        primary = CelesMobBlue,
        onPrimary = Color.White,
        primaryContainer = CelesMobLightBlue.copy(alpha = 0.1f),
        onPrimaryContainer = CelesMobBlue,
        secondary = PurpleGrey40,
        onSecondary = Color.White,
        secondaryContainer = PurpleGrey40.copy(alpha = 0.1f),
        onSecondaryContainer = PurpleGrey40,
        tertiary = Pink40,
        onTertiary = Color.White,
        tertiaryContainer = Pink40.copy(alpha = 0.1f),
        onTertiaryContainer = Pink40,
        background = Color(0xFFFFFBFE),
        onBackground = Color(0xFF1C1B1F),
        surface = Color(0xFFFFFBFE),
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFE7E0EC),
        onSurfaceVariant = Color(0xFF49454F),
        outline = Color(0xFF79747E),
        outlineVariant = Color(0xFFCAC4D0),
        scrim = Color(0xFF000000),
        inverseSurface = Color(0xFF313033),
        inverseOnSurface = Color(0xFFFFFFFF),
        inversePrimary = Color(0xFFADC2FF),
        surfaceTint = CelesMobBlue,
        // Additional colors for specific use cases
        error = CelesMobError,
        onError = Color.White,
        errorContainer = CelesMobError.copy(alpha = 0.1f),
        onErrorContainer = CelesMobError
    )
}

@Composable
private fun darkScheme(): ColorScheme {
    return darkColorScheme(
        primary = CelesMobLightBlue,
        onPrimary = Color.Black,
        primaryContainer = CelesMobBlue.copy(alpha = 0.2f),
        onPrimaryContainer = CelesMobLightBlue,
        secondary = PurpleGrey80,
        onSecondary = Color.Black,
        secondaryContainer = PurpleGrey80.copy(alpha = 0.2f),
        onSecondaryContainer = PurpleGrey80,
        tertiary = Pink80,
        onTertiary = Color.Black,
        tertiaryContainer = Pink80.copy(alpha = 0.2f),
        onTertiaryContainer = Pink80,
        background = Color(0xFF1C1B1F),
        onBackground = Color(0xFFE6E1E5),
        surface = Color(0xFF1C1B1F),
        onSurface = Color(0xFFE6E1E5),
        surfaceVariant = Color(0xFF49454F),
        onSurfaceVariant = Color(0xFFCAC4D0),
        outline = Color(0xFF938F99),
        outlineVariant = Color(0xFF49454F),
        scrim = Color(0xFF000000),
        inverseSurface = Color(0xFFE6E1E5),
        inverseOnSurface = Color(0xFF313033),
        inversePrimary = Color(0xFF445986),
        surfaceTint = CelesMobLightBlue,
        // Additional colors for specific use cases
        error = CelesMobError,
        onError = Color.Black,
        errorContainer = CelesMobError.copy(alpha = 0.2f),
        onErrorContainer = CelesMobError
    )
}