package com.example.kmpapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.kmpapp.presentation.components.AppButton
import com.example.kmpapp.presentation.components.AppButtonStyle
import com.example.kmpapp.presentation.components.InfoCard

@Composable
fun ComponentsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text(
                text = "Компоненты",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Демонстрация UI компонентов",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        
        item { ButtonsSection() }
        item { TextFieldsSection() }
        item { SwitchesAndCheckboxesSection() }
        item { SlidersSection() }
        item { CardsSection() }
    }
}

@Composable
private fun ButtonsSection() {
    ComponentSection(title = "Кнопки") {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Filled Buttons",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AppButton(
                    text = "Filled",
                    onClick = {},
                    style = AppButtonStyle.Filled
                )
                AppButton(
                    text = "С иконкой",
                    onClick = {},
                    icon = Icons.Default.Favorite,
                    style = AppButtonStyle.Filled
                )
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            Text(
                text = "Outlined Buttons",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AppButton(
                    text = "Outlined",
                    onClick = {},
                    style = AppButtonStyle.Outlined
                )
                AppButton(
                    text = "С иконкой",
                    onClick = {},
                    icon = Icons.Default.Send,
                    style = AppButtonStyle.Outlined
                )
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            Text(
                text = "Text Buttons",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AppButton(
                    text = "Text",
                    onClick = {},
                    style = AppButtonStyle.Text
                )
                AppButton(
                    text = "С иконкой",
                    onClick = {},
                    icon = Icons.Default.Info,
                    style = AppButtonStyle.Text
                )
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            Text(
                text = "Icon Buttons",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Home, contentDescription = "Home")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                }
                FilledIconButton(onClick = {}) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
                FilledTonalIconButton(onClick = {}) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                OutlinedIconButton(onClick = {}) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
private fun TextFieldsSection() {
    var textValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    
    ComponentSection(title = "Текстовые поля") {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Обычное поле") },
                placeholder = { Text("Введите текст") },
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = emailValue,
                onValueChange = { 
                    emailValue = it
                    emailError = it.isNotEmpty() && !it.contains("@")
                },
                label = { Text("Email") },
                placeholder = { Text("example@email.com") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                isError = emailError,
                supportingText = {
                    if (emailError) {
                        Text("Введите корректный email адрес")
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = { Text("Пароль") },
                placeholder = { Text("Введите пароль") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            
            TextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Filled TextField") },
                placeholder = { Text("Заполненное поле") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SwitchesAndCheckboxesSection() {
    var switchChecked by remember { mutableStateOf(false) }
    var checkbox1Checked by remember { mutableStateOf(false) }
    var checkbox2Checked by remember { mutableStateOf(true) }
    var checkbox3Checked by remember { mutableStateOf(false) }
    
    ComponentSection(title = "Переключатели и чекбоксы") {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Switches",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Уведомления")
                Switch(
                    checked = switchChecked,
                    onCheckedChange = { switchChecked = it }
                )
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            Text(
                text = "Checkboxes",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = checkbox1Checked,
                    onCheckedChange = { checkbox1Checked = it }
                )
                Text("Опция 1", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = checkbox2Checked,
                    onCheckedChange = { checkbox2Checked = it }
                )
                Text("Опция 2 (выбрана)", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = checkbox3Checked,
                    onCheckedChange = { checkbox3Checked = it }
                )
                Text("Опция 3", modifier = Modifier.padding(start = 8.dp))
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            Text(
                text = "Radio Buttons",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            var selectedOption by remember { mutableStateOf(0) }
            
            Column {
                listOf("Вариант 1", "Вариант 2", "Вариант 3").forEachIndexed { index, text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedOption == index,
                            onClick = { selectedOption = index }
                        )
                        Text(text, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun SlidersSection() {
    var sliderValue by remember { mutableStateOf(0.5f) }
    var discreteSliderValue by remember { mutableStateOf(3f) }
    
    ComponentSection(title = "Слайдеры") {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column {
                Text(
                    text = "Непрерывный слайдер",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Значение: ${(sliderValue * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Column {
                Text(
                    text = "Дискретный слайдер",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Значение: ${discreteSliderValue.toInt()}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Slider(
                    value = discreteSliderValue,
                    onValueChange = { discreteSliderValue = it },
                    valueRange = 0f..5f,
                    steps = 4,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun CardsSection() {
    ComponentSection(title = "Карточки") {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoCard(
                title = "Информационная карточка",
                description = "Карточка с иконкой и описанием для отображения важной информации",
                icon = Icons.Default.Info,
                onClick = {}
            )
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Цветная карточка",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Text(
                        text = "Карточка с кастомным цветом фона",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Elevated Card",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Text(
                        text = "Карточка с тенью для выделения контента",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            
            OutlinedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Build,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Outlined Card",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Text(
                        text = "Карточка с обводкой для легкого визуального разделения",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ComponentSection(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            content()
        }
    }
}
