package com.example.kmpapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.kmpapp.presentation.components.ErrorHandler
import com.example.kmpapp.presentation.components.InfoCard
import com.example.kmpapp.presentation.layout.WindowSize
import com.example.kmpapp.presentation.layout.getContentPadding
import com.example.kmpapp.presentation.layout.getGridColumns
import com.example.kmpapp.presentation.layout.getSpacing
import com.example.kmpapp.presentation.layout.rememberWindowSize
import com.example.kmpapp.presentation.viewmodels.HomeAction
import com.example.kmpapp.presentation.viewmodels.HomeViewModel
import org.koin.compose.koinInject

/**
 * Главный экран приложения с приветствием и карточками информации
 * Поддерживает адаптивную сетку и pull-to-refresh функционал
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsState()
    val windowSize = rememberWindowSize()
    
    // Оптимизация: используем remember для вычисления padding и spacing
    val contentPadding = remember(windowSize) { windowSize.getContentPadding() }
    val spacing = remember(windowSize) { windowSize.getSpacing() }
    
    // Pull-to-refresh state
    val pullToRefreshState = rememberPullToRefreshState()
    
    // Обработка pull-to-refresh
    LaunchedEffect(pullToRefreshState.isRefreshing) {
        if (pullToRefreshState.isRefreshing) {
            viewModel.onAction(HomeAction.RefreshData)
        }
    }
    
    // Сброс состояния refresh когда загрузка завершена
    LaunchedEffect(uiState.isRefreshing) {
        if (!uiState.isRefreshing && pullToRefreshState.isRefreshing) {
            pullToRefreshState.endRefresh()
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            // Приветствие
            Text(
                text = uiState.greeting,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Исследуйте возможности Kotlin Multiplatform",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(spacing * 2))
            
            // Адаптивная сетка карточек
            AdaptiveCardGrid(
                cards = uiState.cards,
                windowSize = windowSize,
                onCardClick = { cardId ->
                    viewModel.onAction(HomeAction.CardClicked(cardId))
                }
            )
        }
        
        // Pull-to-refresh индикатор
        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        
        // Обработчик ошибок
        ErrorHandler(
            error = uiState.error,
            onDismiss = { viewModel.clearError() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * Адаптивная сетка карточек, которая подстраивается под размер экрана
 */
@Composable
private fun AdaptiveCardGrid(
    cards: List<com.example.kmpapp.presentation.viewmodels.CardData>,
    windowSize: WindowSize,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Оптимизация: используем remember для вычисления columns и spacing
    val columns = remember(windowSize) { windowSize.getGridColumns() }
    val spacing = remember(windowSize) { windowSize.getSpacing() }
    
    // Оптимизация: используем remember для contentPadding
    val contentPadding = remember(spacing) { PaddingValues(vertical = spacing) }
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        // Оптимизация: используем key для стабильной идентификации элементов
        items(
            items = cards,
            key = { card -> card.id }
        ) { card ->
            InfoCard(
                title = card.title,
                description = card.description,
                icon = card.icon,
                onClick = { onCardClick(card.id) }
            )
        }
    }
}
