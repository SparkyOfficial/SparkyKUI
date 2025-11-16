import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.kmpapp.presentation.theme.AppTheme
import com.example.kmpapp.presentation.navigation.AppNavigation
import org.koin.compose.KoinContext

@Composable
fun App() {
    // Управление состоянием темы на уровне приложения
    var isDarkTheme by remember { mutableStateOf(false) }
    
    // Предоставление Koin контекста для всего приложения
    KoinContext {
        AppTheme(darkTheme = isDarkTheme) {
            AppNavigation(
                onThemeChange = { newTheme ->
                    isDarkTheme = newTheme
                }
            )
        }
    }
}
