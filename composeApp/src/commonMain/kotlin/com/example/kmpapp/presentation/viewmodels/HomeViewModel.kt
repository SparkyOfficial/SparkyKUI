package com.example.kmpapp.presentation.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
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
    val isRefreshing: Boolean = false
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

    init {
        loadInitialData()
    }

    /**
     * Обработка действий пользователя
     */
    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.CardClicked -> handleCardClick(action.cardId)
            is HomeAction.RefreshData -> refreshData()
        }
    }

    /**
     * Загрузка начальных данных
     */
    private fun loadInitialData() {
        val cards = listOf(
            CardData(
                id = "multiplatform",
                title = "Kotlin Multiplatform",
                description = "Единая кодовая база для всех платформ",
                icon = Icons.Default.Code
            ),
            CardData(
                id = "compose",
                title = "Compose Multiplatform",
                description = "Современный декларативный UI фреймворк",
                icon = Icons.Default.Palette
            ),
            CardData(
                id = "adaptive",
                title = "Адаптивный дизайн",
                description = "Автоматическая адаптация под размер экрана",
                icon = Icons.Default.PhoneAndroid
            ),
            CardData(
                id = "material3",
                title = "Material Design 3",
                description = "Современные компоненты и темы",
                icon = Icons.Default.Settings
            )
        )

        _uiState.update { it.copy(cards = cards) }
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
        _uiState.update { it.copy(isRefreshing = true) }
        
        // Симуляция загрузки данных
        // В реальном приложении здесь был бы вызов репозитория
        loadInitialData()
        
        _uiState.update { it.copy(isRefreshing = false) }
    }
}
