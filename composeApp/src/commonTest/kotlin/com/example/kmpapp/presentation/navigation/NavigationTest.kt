package com.example.kmpapp.presentation.navigation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.example.kmpapp.presentation.theme.AppTheme
import kotlin.test.Test

/**
 * Тесты навигации между экранами
 * Проверяет корректность переключения между различными экранами приложения
 */
@OptIn(ExperimentalTestApi::class)
class NavigationTest {
    
    @Test
    fun navigation_homeScreen_isDisplayedByDefault() = runComposeUiTest {
        // Act
        setContent {
            AppTheme {
                AppNavigation()
            }
        }
        
        // Assert
        onNodeWithText("Добро пожаловать!").assertIsDisplayed()
    }
    
    @Test
    fun navigation_canNavigateToComponents() = runComposeUiTest {
        // Arrange
        setContent {
            AppTheme {
                AppNavigation()
            }
        }
        
        // Act
        onNodeWithText("Компоненты").performClick()
        
        // Assert
        waitForIdle()
        onNodeWithText("UI Компоненты").assertIsDisplayed()
    }
    
    @Test
    fun navigation_canNavigateToSettings() = runComposeUiTest {
        // Arrange
        setContent {
            AppTheme {
                AppNavigation()
            }
        }
        
        // Act
        onNodeWithText("Настройки").performClick()
        
        // Assert
        waitForIdle()
        onNodeWithText("Настройки приложения").assertIsDisplayed()
    }
    
    @Test
    fun navigation_canNavigateBackToHome() = runComposeUiTest {
        // Arrange
        setContent {
            AppTheme {
                AppNavigation()
            }
        }
        
        // Act
        onNodeWithText("Компоненты").performClick()
        waitForIdle()
        onNodeWithText("Главная").performClick()
        
        // Assert
        waitForIdle()
        onNodeWithText("Добро пожаловать!").assertIsDisplayed()
    }
}
