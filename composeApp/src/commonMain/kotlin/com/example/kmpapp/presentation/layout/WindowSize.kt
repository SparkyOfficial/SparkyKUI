package com.example.kmpapp.presentation.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Enum representing different window size classes for adaptive layouts
 */
enum class WindowSize {
    /**
     * Compact size - typically mobile phones (< 600dp width)
     */
    COMPACT,
    
    /**
     * Medium size - typically tablets (600-840dp width)
     */
    MEDIUM,
    
    /**
     * Expanded size - typically desktop (> 840dp width)
     */
    EXPANDED
}

/**
 * Returns the appropriate WindowSize based on the current window width
 */
@Composable
expect fun rememberWindowSize(): WindowSize

/**
 * Helper function to determine WindowSize from width
 */
fun getWindowSizeFromWidth(widthDp: Int): WindowSize {
    return when {
        widthDp < 600 -> WindowSize.COMPACT
        widthDp < 840 -> WindowSize.MEDIUM
        else -> WindowSize.EXPANDED
    }
}

/**
 * Returns adaptive padding based on window size
 */
fun WindowSize.getContentPadding(): Dp {
    return when (this) {
        WindowSize.COMPACT -> 16.dp
        WindowSize.MEDIUM -> 24.dp
        WindowSize.EXPANDED -> 32.dp
    }
}

/**
 * Returns adaptive spacing between elements based on window size
 */
fun WindowSize.getSpacing(): Dp {
    return when (this) {
        WindowSize.COMPACT -> 8.dp
        WindowSize.MEDIUM -> 12.dp
        WindowSize.EXPANDED -> 16.dp
    }
}

/**
 * Returns adaptive card width based on window size
 */
fun WindowSize.getCardWidth(): Dp {
    return when (this) {
        WindowSize.COMPACT -> 280.dp
        WindowSize.MEDIUM -> 320.dp
        WindowSize.EXPANDED -> 360.dp
    }
}

/**
 * Returns number of columns for grid layouts based on window size
 */
fun WindowSize.getGridColumns(): Int {
    return when (this) {
        WindowSize.COMPACT -> 1
        WindowSize.MEDIUM -> 2
        WindowSize.EXPANDED -> 3
    }
}
