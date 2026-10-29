package com.paawan.panchang.core.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = SaffronLight,
    onPrimary = Color(0xFF2C1000),
    primaryContainer = SaffronContainerDark,
    onPrimaryContainer = Color(0xFFFFD9BD),

    secondary = GoldAccent,
    onSecondary = Color(0xFF2D2000),
    secondaryContainer = GoldContainerDark,
    onSecondaryContainer = Color(0xFFFFEFA8),

    tertiary = TempleCrimsonLight,
    onTertiary = Color.White,
    tertiaryContainer = CrimsonContainerDark,
    onTertiaryContainer = Color(0xFFFFCDD2),

    background = DarkBackground,
    onBackground = DarkTextPrimary,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkSurfaceContainer,
    onSurfaceVariant = DarkTextSecondary,

    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,
    error = InauspiciousRed,
    onError = Color.White,
    errorContainer = InauspiciousRedContainerDark,
    onErrorContainer = Color(0xFFFFCDD2)
)

private val LightColorScheme = lightColorScheme(
    primary = SaffronPrimary,
    onPrimary = Color.White,
    primaryContainer = SaffronContainerLight,
    onPrimaryContainer = Color(0xFF5A1E00),

    secondary = GoldSecondary,
    onSecondary = Color(0xFF2D2000),
    secondaryContainer = GoldContainerLight,
    onSecondaryContainer = Color(0xFF574100),

    tertiary = TempleCrimson,
    onTertiary = Color.White,
    tertiaryContainer = CrimsonContainerLight,
    onTertiaryContainer = Color(0xFF490B0B),

    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightSurfaceContainer,
    onSurfaceVariant = LightTextSecondary,

    outline = LightOutline,
    outlineVariant = LightOutlineVariant,
    error = InauspiciousRed,
    onError = Color.White,
    errorContainer = InauspiciousRedContainerLight,
    onErrorContainer = Color(0xFF5B0D0D)
)

@Composable
fun PaawanPanchangTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.surface.toArgb()
            val controller = WindowCompat.getInsetsController(window, view)
            controller.isAppearanceLightStatusBars = !darkTheme
            controller.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
