package com.example.kmpapp.presentation.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Demo composable showing how to use the adaptive layout system
 * This demonstrates the adaptive container, spacing, and layout components
 */
@Composable
fun AdaptiveLayoutDemo() {
    val windowSize = rememberWindowSize()
    
    AdaptiveContainer(
        windowSize = windowSize,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Adaptive Layout Demo",
            style = MaterialTheme.typography.headlineMedium
        )
        
        AdaptiveSpacer(windowSize = windowSize)
        
        Text(
            text = "Current window size: ${windowSize.name}",
            style = MaterialTheme.typography.bodyLarge
        )
        
        AdaptiveSpacer(windowSize = windowSize)
        
        // Demonstrate adaptive layout switching
        AdaptiveLayout(windowSize = windowSize) { size ->
            Box(
                modifier = Modifier
                    .size(windowSize.getCardWidth(), 100.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text("Card 1")
            }
            
            AdaptiveHorizontalSpacer(windowSize = windowSize)
            
            Box(
                modifier = Modifier
                    .size(windowSize.getCardWidth(), 100.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text("Card 2")
            }
        }
        
        AdaptiveSpacer(windowSize = windowSize)
        
        // Show adaptive values
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        ) {
            Text("Adaptive Values:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Content Padding: ${windowSize.getContentPadding()}")
            Text("Spacing: ${windowSize.getSpacing()}")
            Text("Card Width: ${windowSize.getCardWidth()}")
            Text("Grid Columns: ${windowSize.getGridColumns()}")
        }
    }
}
