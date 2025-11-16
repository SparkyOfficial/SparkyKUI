package com.example.kmpapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.kmpapp.presentation.screens.ComponentsScreen
import com.example.kmpapp.presentation.screens.HomeScreen
import com.example.kmpapp.presentation.screens.SettingsScreen

sealed class Screen(
    val icon: ImageVector,
    val title: String
) : Tab {
    
    data object Home : Screen(
        icon = Icons.Default.Home,
        title = "Главная"
    ) {
        override val options: TabOptions
            @Composable
            get() {
                val icon = rememberVectorPainter(Icons.Default.Home)
                return remember {
                    TabOptions(
                        index = 0u,
                        title = "Главная",
                        icon = icon
                    )
                }
            }
        
        @Composable
        override fun Content() {
            HomeScreen()
        }
    }
    
    data object Components : Screen(
        icon = Icons.Default.Star,
        title = "Компоненты"
    ) {
        override val options: TabOptions
            @Composable
            get() {
                val icon = rememberVectorPainter(Icons.Default.Star)
                return remember {
                    TabOptions(
                        index = 1u,
                        title = "Компоненты",
                        icon = icon
                    )
                }
            }
        
        @Composable
        override fun Content() {
            ComponentsScreen()
        }
    }
    
    data object Settings : Screen(
        icon = Icons.Default.Settings,
        title = "Настройки"
    ) {
        override val options: TabOptions
            @Composable
            get() {
                val icon = rememberVectorPainter(Icons.Default.Settings)
                return remember {
                    TabOptions(
                        index = 2u,
                        title = "Настройки",
                        icon = icon
                    )
                }
            }
        
        @Composable
        override fun Content() {
            val onThemeChange = LocalThemeChange.current
            SettingsScreen(onThemeChange = onThemeChange)
        }
    }
}
