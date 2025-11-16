import androidx.compose.runtime.Composable
import com.example.kmpapp.presentation.theme.AppTheme
import com.example.kmpapp.presentation.navigation.AppNavigation

@Composable
fun App() {
    AppTheme {
        AppNavigation()
    }
}
