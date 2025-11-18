package com.example.kmpapp.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Кастомная кнопка приложения с поддержкой различных стилей и иконок
 * Кастомна кнопка застосунку з підтримкою різних стилів та іконок
 * 
 * @param text Текст кнопки / Текст кнопки
 * @param onClick Обработчик нажатия / Обробник натискання
 * @param modifier Модификатор для кастомизации / Модифікатор для кастомізації
 * @param icon Опциональная иконка / Опціональна іконка
 * @param enabled Состояние активности кнопки / Стан активності кнопки
 * @param style Стиль кнопки (Filled, Outlined, Text, Tonal) / Стиль кнопки (Filled, Outlined, Text, Tonal)
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    style: AppButtonStyle = AppButtonStyle.Filled
) {
    // Создаем interaction source для отслеживания hover состояния
    // Створюємо interaction source для відстеження hover стану
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Оптимизация: используем remember для animationSpec
    // Оптимізація: використовуємо remember для animationSpec
    val animationSpec = remember { tween<Float>(durationMillis = 150) }
    
    // Анимация масштаба при hover (для десктопа)
    // Анімація масштабу при hover (для десктопу)
    val scale by animateFloatAsState(
        targetValue = if (isHovered && enabled) 1.02f else 1f,
        animationSpec = animationSpec,
        label = "button_scale"
    )
    
    // Оптимизация: используем remember для buttonModifier
    // Оптимізація: використовуємо remember для buttonModifier
    val buttonModifier = remember(scale) {
        modifier
            .scale(scale)
    }.hoverable(interactionSource = interactionSource)
    
    when (style) {
        AppButtonStyle.Filled -> {
            Button(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                interactionSource = interactionSource
            ) {
                ButtonContent(text = text, icon = icon)
            }
        }
        AppButtonStyle.Outlined -> {
            OutlinedButton(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                interactionSource = interactionSource
            ) {
                ButtonContent(text = text, icon = icon)
            }
        }
        AppButtonStyle.Text -> {
            TextButton(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                interactionSource = interactionSource
            ) {
                ButtonContent(text = text, icon = icon)
            }
        }
        AppButtonStyle.Tonal -> {
            FilledTonalButton(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                interactionSource = interactionSource
            ) {
                ButtonContent(text = text, icon = icon)
            }
        }
    }
}

@Composable
private fun ButtonContent(
    text: String,
    icon: ImageVector?
) {
    if (icon != null) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
    Text(text = text)
}

/**
 * Стили кнопок приложения
 * Стилі кнопок застосунку
 */
enum class AppButtonStyle {
    Filled,    // Заполненная кнопка (по умолчанию) / Заповнена кнопка (за замовчуванням)
    Outlined,  // Кнопка с обводкой / Кнопка з обведенням
    Text,      // Текстовая кнопка / Текстова кнопка
    Tonal      // Тональная кнопка / Тональна кнопка
}
