package com.example.kmpapp.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

/**
 * Индикатор загрузки с опциональным текстом
 * Індикатор завантаження з опціональним текстом
 * 
 * @param modifier Модификатор для кастомизации / Модифікатор для кастомізації
 * @param text Опциональный текст под индикатором / Опціональний текст під індикатором
 * @param size Размер индикатора (Small, Medium, Large) / Розмір індикатора (Small, Medium, Large)
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    text: String? = null,
    size: LoadingIndicatorSize = LoadingIndicatorSize.Medium
) {
    // Создаем бесконечную анимацию для пульсации текста
    // Створюємо нескінченну анімацію для пульсації тексту
    val infiniteTransition = rememberInfiniteTransition(label = "loading_transition")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "text_alpha"
    )
    
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(size.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = when (size) {
                    LoadingIndicatorSize.Small -> 2.dp
                    LoadingIndicatorSize.Medium -> 3.dp
                    LoadingIndicatorSize.Large -> 4.dp
                }
            )
            
            if (text != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.alpha(alpha)
                )
            }
        }
    }
}

/**
 * Размеры индикатора загрузки
 * Розміри індикатора завантаження
 */
enum class LoadingIndicatorSize(val dp: androidx.compose.ui.unit.Dp) {
    Small(24.dp),
    Medium(48.dp),
    Large(64.dp)
}
