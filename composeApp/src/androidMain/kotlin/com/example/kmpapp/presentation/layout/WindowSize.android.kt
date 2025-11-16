package com.example.kmpapp.presentation.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

/**
 * Android implementation of rememberWindowSize
 * Uses LocalConfiguration to get screen width
 */
@Composable
actual fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    return getWindowSizeFromWidth(configuration.screenWidthDp)
}
