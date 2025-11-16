package com.example.kmpapp

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

/**
 * MainActivity - главная активность Android приложения
 * Настраивает Compose UI и splash screen
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Установка splash screen перед вызовом super.onCreate()
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        
        // Включение edge-to-edge режима для современного UI
        enableEdgeToEdge()
        
        // Установка Compose контента
        setContent {
            App()
        }
    }
}
