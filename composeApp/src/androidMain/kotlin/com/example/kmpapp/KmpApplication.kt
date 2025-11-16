package com.example.kmpapp

import android.app.Application
import com.example.kmpapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/**
 * Android Application класс для инициализации Koin
 */
class KmpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Инициализация Koin с Android-специфичной конфигурацией
        initKoin {
            androidLogger(Level.ERROR)
            androidContext(this@KmpApplication)
        }
    }
}
