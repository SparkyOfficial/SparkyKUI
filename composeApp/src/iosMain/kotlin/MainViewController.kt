import androidx.compose.ui.window.ComposeUIViewController
import com.example.kmpapp.di.initKoin

fun MainViewController() = ComposeUIViewController { 
    // Инициализация Koin для iOS платформы
    initKoin()
    
    App() 
}
