package com.example.kmpapp.presentation.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import java.awt.Toolkit

/**
 * Desktop implementation of rememberWindowSize
 * Uses window dimensions to determine size class
 */
@Composable
actual fun rememberWindowSize(): WindowSize {
    val density = LocalDensity.current
    
    // Get the current window width from AWT Toolkit
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val widthDp = with(density) {
        screenSize.width.toDp()
    }
    
    return getWindowSizeFromWidth(widthDp.value.toInt())
}
