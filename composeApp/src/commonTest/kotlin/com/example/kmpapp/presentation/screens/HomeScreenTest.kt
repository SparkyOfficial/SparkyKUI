package com.example.kmpapp.presentation.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.example.kmpapp.presentation.theme.AppTheme
import com.example.kmpapp.presentation.viewmodels.HomeViewModel
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * UI тесты для HomeScreen
 * Проверяет корректность отображения и взаимодействия с элементами главного экрана
 */
@OptIn(ExperimentalTestApi::class)
class HomeScreenTest {
    
    @Test
    fun homeScreen_displaysGreeting() = runComposeUiTest {
        // Arrange
        val viewModel = HomeViewModel()
        
        // Act
        setContent {
            AppTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
        
        // Assert
        onNodeWithText("Добро пожаловать!").assertIsDisplayed()
    }
    
    @Test
    fun homeScreen_displaysSubtitle() = runComposeUiTest {
        // Arrange
        val viewModel = HomeViewModel()
        
        // Act
        setContent {
            AppTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
        
        // Assert
        onNodeWithText("Исследуйте возможности Kotlin Multiplatform").assertIsDisplayed()
    }
    
    @Test
    fun homeScreen_displaysAllCards() = runComposeUiTest {
        // Arrange
        val viewModel = HomeViewModel()
        
        // Act
        setContent {
            AppTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
        
        // Assert
        onNodeWithText("Kotlin Multiplatform").assertIsDisplayed()
        onNodeWithText("Compose Multiplatform").assertIsDisplayed()
        onNodeWithText("Адаптивный дизайн").assertIsDisplayed()
        onNodeWithText("Material Design 3").assertIsDisplayed()
    }
    
    @Test
    fun homeScreen_cardClick_updatesViewModel() = runComposeUiTest {
        // Arrange
        val viewModel = HomeViewModel()
        
        setContent {
            AppTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
        
        // Act
        onNodeWithText("Kotlin Multiplatform").performClick()
        
        // Assert
        waitForIdle()
        assertEquals("multiplatform", viewModel.uiState.value.selectedCardId)
    }
}
