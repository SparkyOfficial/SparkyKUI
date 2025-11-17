package com.example.kmpapp.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Информационная карточка с иконкой, заголовком и описанием
 * 
 * @param title Заголовок карточки
 * @param description Описание карточки
 * @param icon Иконка карточки
 * @param onClick Обработчик нажатия на карточку
 * @param modifier Модификатор для кастомизации
 */
@Composable
fun InfoCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Создаем interaction source для отслеживания hover состояния
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Оптимизация: используем remember для animationSpec
    val animationSpec = remember { tween<Float>(durationMillis = 200) }
    val dpAnimationSpec = remember { tween<androidx.compose.ui.unit.Dp>(durationMillis = 200) }
    val colorAnimationSpec = remember { tween<androidx.compose.ui.graphics.Color>(durationMillis = 200) }
    
    // Анимация elevation при hover
    val elevation by animateDpAsState(
        targetValue = if (isHovered) 8.dp else 2.dp,
        animationSpec = dpAnimationSpec,
        label = "card_elevation"
    )
    
    // Анимация масштаба при hover
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = animationSpec,
        label = "card_scale"
    )
    
    // Анимация цвета контейнера при hover
    val containerColor by animateColorAsState(
        targetValue = if (isHovered) 
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f)
        else 
            MaterialTheme.colorScheme.surfaceVariant,
        animationSpec = colorAnimationSpec,
        label = "card_color"
    )
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .hoverable(interactionSource = interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Иконка с анимацией вращения при hover
            val iconRotation by animateFloatAsState(
                targetValue = if (isHovered) 5f else 0f,
                animationSpec = tween(durationMillis = 200),
                label = "icon_rotation"
            )
            
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Текстовый контент
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
    }
}
