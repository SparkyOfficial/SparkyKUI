package com.example.kmpapp.presentation.viewmodels

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Unit тесты для HomeViewModel
 * Проверяет корректность управления состоянием главного экрана
 */
class HomeViewModelTest {
    
    @Test
    fun `initial state should have default values`() {
        // Arrange & Act
        val viewModel = HomeViewModel()
        val state = viewModel.uiState.value
        
        // Assert
        assertEquals("Добро пожаловать!", state.greeting)
        assertFalse(state.isLoading)
        assertFalse(state.isRefreshing)
        assertNull(state.selectedCardId)
        assertNull(state.error)
        assertEquals(4, state.cards.size)
    }
    
    @Test
    fun `initial state should load default cards`() {
        // Arrange & Act
        val viewModel = HomeViewModel()
        val state = viewModel.uiState.value
        
        // Assert
        assertEquals(4, state.cards.size)
        assertEquals("multiplatform", state.cards[0].id)
        assertEquals("Kotlin Multiplatform", state.cards[0].title)
        assertEquals("compose", state.cards[1].id)
        assertEquals("adaptive", state.cards[2].id)
        assertEquals("material3", state.cards[3].id)
    }
    
    @Test
    fun `when card clicked should update selected card state`() {
        // Arrange
        val viewModel = HomeViewModel()
        
        // Act
        viewModel.onAction(HomeAction.CardClicked("multiplatform"))
        
        // Assert
        assertEquals("multiplatform", viewModel.uiState.value.selectedCardId)
    }
    
    @Test
    fun `when different card clicked should update to new card id`() {
        // Arrange
        val viewModel = HomeViewModel()
        viewModel.onAction(HomeAction.CardClicked("multiplatform"))
        
        // Act
        viewModel.onAction(HomeAction.CardClicked("compose"))
        
        // Assert
        assertEquals("compose", viewModel.uiState.value.selectedCardId)
    }
    
    @Test
    fun `when refresh data should maintain cards`() {
        // Arrange
        val viewModel = HomeViewModel()
        val initialCards = viewModel.uiState.value.cards
        
        // Act
        viewModel.onAction(HomeAction.RefreshData)
        
        // Assert
        val state = viewModel.uiState.value
        assertFalse(state.isRefreshing)
        assertEquals(initialCards.size, state.cards.size)
    }
    
    @Test
    fun `clearError should remove error from state`() {
        // Arrange
        val viewModel = HomeViewModel()
        
        // Act
        viewModel.clearError()
        
        // Assert
        assertNull(viewModel.uiState.value.error)
    }
}
