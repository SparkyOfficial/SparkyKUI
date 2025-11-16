package com.example.kmpapp.presentation.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Состояние экрана настроек
 */
data class SettingsUiState(
    val isDarkTheme: Boolean = false,
    val appVersion: String = "1.0.0",
    val appName: String = "Kotlin Multiplatform App"
)

/**
 * Действия пользователя на экране настроек
 */
sealed class SettingsAction {
    object ToggleTheme : SettingsAction()
}

/**
 * ViewModel для управления настройками приложения
 * Управляет состоянием темы (светлая/темная) и другими настройками
 */
class SettingsViewModel {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()
    
    /**
     * StateFlow для состояния темы (светлая/темная)
     * Используется для управления темой приложения
     */
    val isDarkTheme: StateFlow<Boolean> = MutableStateFlow(false).apply {
        // Синхронизация с uiState
        _uiState.value = _uiState.value.copy(isDarkTheme = value)
    }
    
    /**
     * Обработка действий пользователя
     */
    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.ToggleTheme -> toggleTheme()
        }
    }
    
    /**
     * Переключение темы между светлой и темной
     */
    fun toggleTheme() {
        _uiState.update { currentState ->
            currentState.copy(isDarkTheme = !currentState.isDarkTheme)
        }
    }
}
