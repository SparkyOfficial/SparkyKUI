package com.example.kmpapp.presentation.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.example.kmpapp.presentation.theme.AppTheme
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Тесты адаптивной верстки
 * Проверяет корректность работы адаптивной системы компоновки
 */
@OptIn(ExperimentalTestApi::class)
class AdaptiveLayoutTest {
    
    @Test
    fun windowSize_compact_hasCorrectPadding() {
        // Arrange
        val windowSize = WindowSize.COMPACT
        
        // Act
        val padding = windowSize.getContentPadding()
        
        // Assert
        assertEquals(16.dp, padding)
    }
    
    @Test
    fun windowSize_medium_hasCorrectPadding() {
        // Arrange
        val windowSize = WindowSize.MEDIUM
        
        // Act
        val padding = windowSize.getContentPadding()
        
        // Assert
        assertEquals(24.dp, padding)
    }
    
    @Test
    fun windowSize_expanded_hasCorrectPadding() {
        // Arrange
        val windowSize = WindowSize.EXPANDED
        
        // Act
        val padding = windowSize.getContentPadding()
        
        // Assert
        assertEquals(32.dp, padding)
    }
    
    @Test
    fun windowSize_compact_hasCorrectGridColumns() {
        // Arrange
        val windowSize = WindowSize.COMPACT
        
        // Act
        val columns = windowSize.getGridColumns()
        
        // Assert
        assertEquals(1, columns)
    }
    
    @Test
    fun windowSize_medium_hasCorrectGridColumns() {
        // Arrange
        val windowSize = WindowSize.MEDIUM
        
        // Act
        val columns = windowSize.getGridColumns()
        
        // Assert
        assertEquals(2, columns)
    }
    
    @Test
    fun windowSize_expanded_hasCorrectGridColumns() {
        // Arrange
        val windowSize = WindowSize.EXPANDED
        
        // Act
        val columns = windowSize.getGridColumns()
        
        // Assert
        assertEquals(3, columns)
    }
    
    @Test
    fun adaptiveContainer_displaysContent() = runComposeUiTest {
        // Act
        setContent {
            AppTheme {
                AdaptiveContainer {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text("Test Content")
                    }
                }
            }
        }
        
        // Assert
        onNodeWithText("Test Content").assertIsDisplayed()
    }
}
