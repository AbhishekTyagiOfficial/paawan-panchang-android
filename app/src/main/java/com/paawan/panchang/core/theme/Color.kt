package com.paawan.panchang.core.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// =========================================================================
// Spiritual Vedic Color System: Saffron, Gold & Sacred Crimson
// =========================================================================

// Primary Saffron (Bhagwa) Tones
val SaffronPrimary = Color(0xFFE65100)          // Radiant Sacred Saffron
val SaffronPrimaryVariant = Color(0xFFBF360C)   // Deep Terracotta Saffron
val SaffronLight = Color(0xFFFF8C00)            // Warm Marigold Saffron
val SaffronContainerLight = Color(0xFFFFE8D6)   // Soft Warm Saffron Tint
val SaffronContainerDark = Color(0xFF3E1D08)    // Deep Obsidian Saffron Tint

// Secondary Golden Marigold Tones
val GoldSecondary = Color(0xFFD4AF37)          // Regal Temple Gold
val GoldAccent = Color(0xFFFFB300)             // Sunlit Golden Amber
val GoldContainerLight = Color(0xFFFFF4D4)     // Radiant Buttergold
val GoldContainerDark = Color(0xFF382A08)      // Muted Bronzed Gold

// Tertiary Sacred Vermillion / Kumkum
val TempleCrimson = Color(0xFFB71C1C)          // Sacred Kumkum Crimson
val TempleCrimsonLight = Color(0xFFD32F2F)     // Festive Crimson
val CrimsonContainerLight = Color(0xFFFFEBEE)  // Pale Lotus Petal
val CrimsonContainerDark = Color(0xFF3B1010)   // Deep Temple Twilight

// Astrological Indicators (Shubh & Ashubh)
val AuspiciousGreen = Color(0xFF2E7D32)        // Shubh Muhurat Green
val AuspiciousGreenLight = Color(0xFF4CAF50)
val AuspiciousGreenContainerLight = Color(0xFFE8F5E9)
val AuspiciousGreenContainerDark = Color(0xFF102812)

val InauspiciousRed = Color(0xFFC62828)        // Rahu Kalam Caution Red
val InauspiciousRedContainerLight = Color(0xFFFFEBEE)
val InauspiciousRedContainerDark = Color(0xFF330D0D)

// Light Theme Surface & Text Hierarchy
val LightBackground = Color(0xFFFAF7F2)         // Sacred Parchment Cream
val LightSurface = Color(0xFFFFFFFF)            // Pure Altar White
val LightSurfaceContainer = Color(0xFFF3ECE0)   // Warm Sandalwood Container
val LightSurfaceContainerHigh = Color(0xFFECE3D4)
val LightOutline = Color(0xFFE0D3C1)            // Delicate Incense Border
val LightOutlineVariant = Color(0xFFECE4D8)
val LightTextPrimary = Color(0xFF20160F)        // Deep Aged Teak Wood
val LightTextSecondary = Color(0xFF635245)      // Warm Earth Gray
val LightTextTertiary = Color(0xFF948173)       // Soft Sandstone

// Dark Theme Surface & Text Hierarchy
val DarkBackground = Color(0xFF120E0B)          // Temple Sanctum Midnight
val DarkSurface = Color(0xFF1C1611)             // Dark Polished Teak
val DarkSurfaceContainer = Color(0xFF251E17)    // Elevated Night Sandalwood
val DarkSurfaceContainerHigh = Color(0xFF30271E)
val DarkOutline = Color(0xFF403429)             // Deep Bronze Border
val DarkOutlineVariant = Color(0xFF2D241C)
val DarkTextPrimary = Color(0xFFF7EFE8)         // Warm White Jasmine
val DarkTextSecondary = Color(0xFFCBB9AA)       // Soft Ivory
val DarkTextTertiary = Color(0xFF998574)        // Muted Incense Ash

// =========================================================================
// Sacred Gradients
// =========================================================================
val SaffronGoldGradient = Brush.linearGradient(
    colors = listOf(SaffronPrimary, GoldAccent)
)

val SunburstHeaderGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFFE65100), Color(0xFFFF8C00), Color(0xFFFFA000))
)

val DarkHeaderGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF2D1705), Color(0xFF1C1611))
)

val CardGlowGradientLight = Brush.verticalGradient(
    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF9F2))
)

val CardGlowGradientDark = Brush.verticalGradient(
    colors = listOf(Color(0xFF231C15), Color(0xFF1A140F))
)
