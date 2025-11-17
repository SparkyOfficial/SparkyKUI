package com.example.kmpapp.presentation.viewmodels

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Unit тесты для SettingsViewModel
 * Проверяет корректность управления настройками приложения
 */
class SettingsViewModelTest {
    
    @Test
    fun `initial state should have default values`() {
        // Arrange & Act
        val viewModel = SettingsViewModel()
        val state = viewModel.uiState.value
        
        // Assert
        assertFalse(state.isDarkTheme)
        assertEquals("1.0.0", state.appVersion)
        assertEquals("Kotlin Multiplatform App", state.appName)
        assertNull(state.error)
    }
    
    @Test
    fun `toggleTheme should switch from light to dark`() {
        // Arrange
        val viewModel = SettingsViewModel()
        val initialTheme = viewModel.uiState.value.isDarkTheme
        
        // Act
        viewModel.toggleTheme()
        
        // Assert
        assertEquals(!initialTheme, viewModel.uiState.value.isDarkTheme)
    }
    
    @Test
    fun `toggleTheme should switch from dark to light`() {
        // Arrange
        val viewModel = SettingsViewModel()
        viewModel.toggleTheme() // Switch to dark
        
        // Act
        viewModel.toggleTheme() // Switch back to light
        
        // Assert
        assertFalse(viewModel.uiState.value.isDarkTheme)
    }
    
    @Test
    fun `toggleTheme multiple times should toggle correctly`() {
        // Arrange
        val viewModel = SettingsViewModel()
        
        // Act & Assert
        assertFalse(viewModel.uiState.value.isDarkTheme)
        
        viewModel.toggleTheme()
        assertTrue(viewModel.uiState.value.isDarkTheme)
        
        viewModel.toggleTheme()
        assertFalse(viewModel.uiState.value.isDarkTheme)
        
        viewModel.toggleTheme()
        assertTrue(viewModel.uiState.value.isDarkTheme)
    }
    
    @Test
    fun `onAction with ToggleTheme should change theme`() {
        // Arrange
        val viewModel = SettingsViewModel()
        
        // Act
        viewModel.onAction(SettingsAction.ToggleTheme)
        
        // Assert
        assertTrue(viewModel.uiState.value.isDarkTheme)
    }
    
    @Test
    fun `clearError should remove error from state`() {
        // Arrange
        val viewModel = SettingsViewModel()
        
        // Act
        viewModel.clearError()
        
        // Assert
        assertNull(viewModel.uiState.value.error)
    }
    
    @Test
    fun `app version should remain constant`() {
        // Arrange
        val viewModel = SettingsViewModel()
        
        // Act
        viewModel.toggleTheme()
        
        // Assert
        assertEquals("1.0.0", viewModel.uiState.value.appVersion)
    }
    
    @Test
    fun `app name should remain constant`() {
        // Arrange
        val viewModel = SettingsViewModel()
        
        // Act
        viewModel.toggleTheme()
        
        // Assert
        assertEquals("Kotlin Multiplatform App", viewModel.uiState.value.appName)
    }
}
