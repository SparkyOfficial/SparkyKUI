package com.example.kmpapp.presentation.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
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
            val tabNavigator = LocalTabNavigator.current
            
            when (windowSize) {
                WindowSize.COMPACT -> {
                    // Мобильная компоновка с нижней навигацией
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            BottomNavigationBar(tabs = tabs)
                        }
                    ) { paddingValues ->
                        // Плавные переходы между экранами
                        AnimatedContent(
                            targetState = tabNavigator.current,
                            transitionSpec = {
                                fadeIn(animationSpec = tween(300)) togetherWith
                                fadeOut(animationSpec = tween(300))
                            },
                            label = "screen_transition"
                        ) {
                            CurrentTab()
                        }
                    }
                }
                WindowSize.MEDIUM, WindowSize.EXPANDED -> {
                    // Десктопная компоновка с боковой навигацией
                    Row(modifier = Modifier.fillMaxSize()) {
                        SideNavigationRail(tabs = tabs)
                        // Плавные переходы между экранами
                        AnimatedContent(
                            targetState = tabNavigator.current,
                            transitionSpec = {
                                fadeIn(animationSpec = tween(300)) togetherWith
                                fadeOut(animationSpec = tween(300))
                            },
                            label = "screen_transition",
                            modifier = Modifier.weight(1f)
                        ) {
                            CurrentTab()
                        }
                    }
                }
            }
        }
    }
}
