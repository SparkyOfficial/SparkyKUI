import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.kmpapp.di.initKoin

fun main() = application {
    // Инициализация Koin для Desktop платформы
    initKoin()
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kotlin Multiplatform App",
        state = rememberWindowState(width = 1200.dp, height = 800.dp)
    ) {
        App()
    }
}
