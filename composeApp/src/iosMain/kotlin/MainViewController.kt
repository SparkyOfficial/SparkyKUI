import androidx.compose.ui.window.ComposeUIViewController
import com.example.kmpapp.di.initKoin

/**
 * MainViewController для iOS приложения
 * Создает UIViewController с Compose UI контентом
 * Вызывается из Swift/Objective-C кода iOS приложения
 */
fun MainViewController() = ComposeUIViewController { 
    // Инициализация Koin для iOS платформы
    initKoin()
    
    // Запуск главного Compose приложения
    App() 
}
