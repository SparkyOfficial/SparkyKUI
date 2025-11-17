package com.example.kmpapp.presentation.layout

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Adaptive container that automatically adjusts layout based on window size
 * Provides consistent padding and spacing across different screen sizes
 */
@Composable
fun AdaptiveContainer(
    modifier: Modifier = Modifier,
    windowSize: WindowSize = rememberWindowSize(),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(windowSize.getContentPadding()),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement
    ) {
        content()
    }
}

/**
 * Adaptive row container for horizontal layouts
 */
@Composable
fun AdaptiveRow(
    modifier: Modifier = Modifier,
    windowSize: WindowSize = rememberWindowSize(),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(windowSize.getContentPadding()),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        content()
    }
}

/**
 * Adaptive spacer that adjusts size based on window size
 */
@Composable
fun AdaptiveSpacer(
    modifier: Modifier = Modifier,
    windowSize: WindowSize = rememberWindowSize()
) {
    Spacer(modifier = modifier.height(windowSize.getSpacing()))
}

/**
 * Adaptive horizontal spacer
 */
@Composable
fun AdaptiveHorizontalSpacer(
    modifier: Modifier = Modifier,
    windowSize: WindowSize = rememberWindowSize()
) {
    Spacer(modifier = modifier.width(windowSize.getSpacing()))
}

/**
 * Adaptive layout that switches between vertical and horizontal based on window size
 * with smooth transitions when window size changes
 */
@Composable
fun AdaptiveLayout(
    modifier: Modifier = Modifier,
    windowSize: WindowSize = rememberWindowSize(),
    content: @Composable (WindowSize) -> Unit
) {
    // Плавный переход при изменении размера окна
    AnimatedContent(
        targetState = windowSize,
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith
            fadeOut(animationSpec = tween(300))
        },
        label = "adaptive_layout_transition"
    ) { targetWindowSize ->
        when (targetWindowSize) {
            WindowSize.COMPACT -> {
                // Vertical layout for mobile
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(targetWindowSize.getContentPadding()),
                    verticalArrangement = Arrangement.spacedBy(targetWindowSize.getSpacing())
                ) {
                    content(targetWindowSize)
                }
            }
            WindowSize.MEDIUM, WindowSize.EXPANDED -> {
                // Horizontal layout for tablets and desktop
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(targetWindowSize.getContentPadding()),
                    horizontalArrangement = Arrangement.spacedBy(targetWindowSize.getSpacing())
                ) {
                    content(targetWindowSize)
                }
            }
        }
    }
}

/**
 * Adaptive grid layout that adjusts columns based on window size
 */
@Composable
fun AdaptiveGrid(
    modifier: Modifier = Modifier,
    windowSize: WindowSize = rememberWindowSize(),
    content: @Composable () -> Unit
) {
    val columns = windowSize.getGridColumns()
    val spacing = windowSize.getSpacing()
    
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        content()
    }
}

/**
 * Extension function to apply adaptive padding
 */
fun Modifier.adaptivePadding(windowSize: WindowSize): Modifier {
    return this.padding(windowSize.getContentPadding())
}

/**
 * Extension function to apply adaptive spacing
 */
fun Modifier.adaptiveSpacing(windowSize: WindowSize): Modifier {
    return this.padding(windowSize.getSpacing())
}
