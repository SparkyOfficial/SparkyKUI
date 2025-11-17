package com.example.kmpapp.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpapp.presentation.components.ErrorHandler
import com.example.kmpapp.presentation.layout.rememberWindowSize
import com.example.kmpapp.presentation.layout.getContentPadding
import com.example.kmpapp.presentation.layout.getSpacing
import com.example.kmpapp.presentation.viewmodels.SettingsAction
import com.example.kmpapp.presentation.viewmodels.SettingsViewModel
import org.koin.compose.koinInject

/**
 * Экран настроек приложения
 * Содержит переключатель темы и информацию о приложении
 * 
 * @param viewModel ViewModel для управления настройками
 * @param onThemeChange Callback для изменения темы на уровне приложения
 */
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinInject(),
    onThemeChange: ((Boolean) -> Unit)? = null
) {
    val uiState by viewModel.uiState.collectAsState()
    val windowSize = rememberWindowSize()
    
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(windowSize.getContentPadding()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
        // Заголовок
        Text(
            text = "Настройки",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Настройте приложение под себя",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        
        Spacer(modifier = Modifier.height(windowSize.getSpacing() * 2))
        
        // Секция настроек темы
        SettingsSection(
            title = "Внешний вид"
        ) {
            ThemeSettingItem(
                isDarkTheme = uiState.isDarkTheme,
                onThemeToggle = {
                    viewModel.onAction(SettingsAction.ToggleTheme)
                    onThemeChange?.invoke(!uiState.isDarkTheme)
                }
            )
        }
        
        Spacer(modifier = Modifier.height(windowSize.getSpacing()))
        
        // Секция "О приложении"
        SettingsSection(
            title = "О приложении"
        ) {
            AboutAppItem(
                appName = uiState.appName,
                appVersion = uiState.appVersion
            )
        }
        }
        
        // Обработчик ошибок
        ErrorHandler(
            error = uiState.error,
            onDismiss = { viewModel.clearError() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * Секция настроек с заголовком
 */
@Composable
private fun SettingsSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            content()
        }
    }
}

/**
 * Элемент настройки темы с переключателем
 */
@Composable
private fun ThemeSettingItem(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Анимация цвета иконки
    val iconColor by animateColorAsState(
        targetValue = if (isDarkTheme) 
            MaterialTheme.colorScheme.primary 
        else 
            MaterialTheme.colorScheme.tertiary,
        animationSpec = tween(durationMillis = 300),
        label = "icon_color"
    )
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                contentDescription = null,
                tint = iconColor
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = if (isDarkTheme) "Темная тема" else "Светлая тема",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Переключение между светлой и темной темой",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        // Переключатель с визуальной обратной связью
        Switch(
            checked = isDarkTheme,
            onCheckedChange = { onThemeToggle() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.outline,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}

/**
 * Элемент с информацией о приложении
 */
@Composable
private fun AboutAppItem(
    appName: String,
    appVersion: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Название приложения",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
                
                Text(
                    text = appName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Версия",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
                
                Text(
                    text = appVersion,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
