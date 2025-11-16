import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.kmpapp.di.initKoin
import java.awt.Dimension

/**
 * Точка входа для Desktop приложения
 * Настраивает окно приложения с размером 1200x800dp
 */
fun main() = application {
    // Инициализация Koin для Desktop платформы
    initKoin()
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kotlin Multiplatform App",
        state = rememberWindowState(width = 1200.dp, height = 800.dp)
    ) {
        // Установка минимального размера окна
        window.minimumSize = Dimension(800, 600)
        
        App()
    }
}
