package com.example.kmpapp.presentation.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.kmpapp.domain.error.AppError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Данные карточки для отображения на главном экране
 */
data class CardData(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector
)

/**
 * Состояние главного экрана
 */
data class HomeUiState(
    val greeting: String = "Добро пожаловать!",
    val isLoading: Boolean = false,
    val cards: List<CardData> = emptyList(),
    val selectedCardId: String? = null,
    val isRefreshing: Boolean = false,
    val error: AppError? = null
)

/**
 * Действия пользователя на главном экране
 */
sealed class HomeAction {
    data class CardClicked(val cardId: String) : HomeAction()
    object RefreshData : HomeAction()
}

/**
 * ViewModel для управления состоянием главного экрана
 */
class HomeViewModel {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    // Оптимизация: создаем неизменяемый список карточек один раз
    private val defaultCards = listOf(
        CardData(
            id = "multiplatform",
            title = "Kotlin Multiplatform",
            description = "Единая кодовая база для всех платформ",
            icon = Icons.Default.Build
        ),
        CardData(
            id = "compose",
            title = "Compose Multiplatform",
            description = "Современный декларативный UI фреймворк",
            icon = Icons.Default.Favorite
        ),
        CardData(
            id = "adaptive",
            title = "Адаптивный дизайн",
            description = "Автоматическая адаптация под размер экрана",
            icon = Icons.Default.Phone
        ),
        CardData(
            id = "material3",
            title = "Material Design 3",
            description = "Современные компоненты и темы",
            icon = Icons.Default.Settings
        )
    )

    init {
        loadInitialData()
    }

    /**
     * Обработка действий пользователя
     */
    fun onAction(action: HomeAction) {
        try {
            when (action) {
                is HomeAction.CardClicked -> handleCardClick(action.cardId)
                is HomeAction.RefreshData -> refreshData()
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
        _uiState.update { it.copy(error = error, isLoading = false, isRefreshing = false) }
    }

    /**
     * Загрузка начальных данных
     */
    private fun loadInitialData() {
        try {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            // Оптимизация: используем предварительно созданный список
            _uiState.update { it.copy(cards = defaultCards, isLoading = false) }
        } catch (e: Exception) {
            handleError(AppError.UnknownError(e))
        }
    }

    /**
     * Обработка клика по карточке
     */
    private fun handleCardClick(cardId: String) {
        _uiState.update { it.copy(selectedCardId = cardId) }
    }

    /**
     * Обновление данных (pull-to-refresh)
     */
    private fun refreshData() {
        try {
            _uiState.update { it.copy(isRefreshing = true, error = null) }
            
            // Симуляция загрузки данных
            // В реальном приложении здесь был бы вызов репозитория
            loadInitialData()
            
            _uiState.update { it.copy(isRefreshing = false) }
        } catch (e: Exception) {
            handleError(AppError.UnknownError(e))
        }
    }
}
