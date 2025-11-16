package com.example.kmpapp.presentation.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.kmpapp.presentation.layout.WindowSize
import com.example.kmpapp.presentation.layout.rememberWindowSize

@Composable
fun AppNavigation() {
    val windowSize = rememberWindowSize()
    val tabs = listOf(Screen.Home, Screen.Components, Screen.Settings)
    
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
