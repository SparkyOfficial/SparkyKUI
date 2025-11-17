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
 */
val LocalThemeChange = compositionLocalOf<((Boolean) -> Unit)?> { null }

@Composable
fun AppNavigation(
    onThemeChange: (Boolean) -> Unit
) {
    val windowSize = rememberWindowSize()
    
    // Оптимизация: используем remember для списка tabs
    val tabs = remember { listOf(Screen.Home, Screen.Components, Screen.Settings) }
    
    CompositionLocalProvider(LocalThemeChange provides onThemeChange) {
        TabNavigator(Screen.Home) {
            when (windowSize) {
                WindowSize.COMPACT -> {
                    // Мобильная компоновка с нижней навигацией
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
                    Row(modifier = Modifier.fillMaxSize()) {
                        SideNavigationRail(tabs = tabs)
                        CurrentTab()
                    }
                }
            }
        }
    }
}
