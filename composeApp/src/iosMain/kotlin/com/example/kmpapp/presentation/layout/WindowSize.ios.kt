package com.example.kmpapp.presentation.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo

/**
 * iOS implementation of rememberWindowSize
 * Uses LocalWindowInfo to get screen dimensions
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun rememberWindowSize(): WindowSize {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    
    val widthDp = with(density) {
        windowInfo.containerSize.width.toDp()
    }
    
    return getWindowSizeFromWidth(widthDp.value.toInt())
}
