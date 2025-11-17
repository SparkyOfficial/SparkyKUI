package com.example.kmpapp.presentation.viewmodels

import com.example.kmpapp.domain.error.AppError
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
    val appName: String = "Kotlin Multiplatform App",
    val error: AppError? = null
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
        try {
            when (action) {
                is SettingsAction.ToggleTheme -> toggleTheme()
            }
        } catch (e: Exception) {
            handleError(AppError.UnknownError(e))
        }
    }
    
    /**
     * Переключение темы между светлой и темной
     */
    fun toggleTheme() {
        try {
            _uiState.update { currentState ->
                currentState.copy(isDarkTheme = !currentState.isDarkTheme, error = null)
            }
        } catch (e: Exception) {
            handleError(AppError.UnknownError(e))
        }
    }
    
    /**
     * Очистка ошибки
     */
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
    
    /**
     * Обработка ошибки
     */
    private fun handleError(error: AppError) {
        _uiState.update { it.copy(error = error) }
    }
}
