package com.example.kmpapp.presentation.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.kmpapp.domain.error.AppError

/**
 * Компонент для отображения ошибок через Snackbar
 * Компонент для відображення помилок через Snackbar
 * Автоматически показывает Snackbar при появлении ошибки
 * Автоматично показує Snackbar при появі помилки
 * 
 * @param error Текущая ошибка для отображения (null если ошибок нет) / Поточна помилка для відображення (null якщо помилок немає)
 * @param onDismiss Callback для закрытия ошибки / Callback для закриття помилки
 * @param modifier Модификатор для кастомизации / Модифікатор для кастомізації
 */
@Composable
fun ErrorHandler(
    error: AppError?,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    
    // Показываем Snackbar при появлении ошибки
    // Показуємо Snackbar при появі помилки
    LaunchedEffect(error) {
        error?.let {
            snackbarHostState.showSnackbar(
                message = it.toUserMessage(),
                actionLabel = "OK"
            )
            onDismiss()
        }
    }
    
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier
    ) { data ->
        Snackbar(
            action = {
                TextButton(onClick = { data.dismiss() }) {
                    Text(data.visuals.actionLabel ?: "OK")
                }
            }
        ) {
            Text(data.visuals.message)
        }
    }
}
