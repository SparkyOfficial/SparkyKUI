package com.example.kmpapp.presentation.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.kmpapp.presentation.layout.WindowSize
import com.example.kmpapp.presentation.layout.rememberWindowSize

/**
 * CompositionLocal для передачи callback изменения темы вниз по дереву композиции
 * CompositionLocal для передачі callback зміни теми вниз по дереву композиції
 */
val LocalThemeChange = compositionLocalOf<((Boolean) -> Unit)?> { null }

@Composable
fun AppNavigation(
    onThemeChange: (Boolean) -> Unit
) {
    val windowSize = rememberWindowSize()
    
    // Оптимизация: используем remember для списка tabs
    // Оптимізація: використовуємо remember для списку tabs
    val tabs = remember { listOf(Screen.Home, Screen.Components, Screen.Settings) }
    
    CompositionLocalProvider(LocalThemeChange provides onThemeChange) {
        TabNavigator(Screen.Home) {
            when (windowSize) {
                WindowSize.COMPACT -> {
                    // Мобильная компоновка с нижней навигацией
                    // Мобільна компоновка з нижньою навігацією
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            BottomNavigationBar(tabs = tabs)
                        }
                    ) { paddingValues ->
                        CurrentTab()
                    }
                }
                WindowSize.MEDIUM, WindowSize.EXPANDED -> {
                    // Десктопная компоновка с боковой навигацией
                    // Десктопна компоновка з бічною навігацією
                    Row(modifier = Modifier.fillMaxSize()) {
                        SideNavigationRail(tabs = tabs)
                        CurrentTab()
                    }
                }
            }
        }
    }
}
