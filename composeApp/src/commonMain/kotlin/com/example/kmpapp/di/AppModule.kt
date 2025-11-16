package com.example.kmpapp.di

import com.example.kmpapp.presentation.viewmodels.HomeViewModel
import com.example.kmpapp.presentation.viewmodels.SettingsViewModel
import org.koin.dsl.module

/**
 * Модуль Koin для ViewModels
 * Определяет зависимости для всех ViewModels приложения
 */
val appModule = module {
    // ViewModels
    factory { HomeViewModel() }
    factory { SettingsViewModel() }
}

/**
 * Список всех модулей приложения
 */
val allModules = listOf(
    appModule
)
