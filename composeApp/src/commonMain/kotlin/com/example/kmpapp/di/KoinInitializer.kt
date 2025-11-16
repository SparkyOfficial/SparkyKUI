package com.example.kmpapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Инициализация Koin для приложения
 * Вызывается при запуске приложения на каждой платформе
 * 
 * @param appDeclaration Дополнительная конфигурация Koin (опционально)
 */
fun initKoin(appDeclaration: KoinAppDeclaration? = null) {
    startKoin {
        appDeclaration?.invoke(this)
        modules(allModules)
    }
}
