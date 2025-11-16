package com.example.kmpapp.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun BottomNavigationBar(
    tabs: List<Tab>,
    modifier: Modifier = Modifier
) {
    val tabNavigator = LocalTabNavigator.current
    
    NavigationBar(modifier = modifier) {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = tabNavigator.current == tab,
                onClick = { tabNavigator.current = tab },
                icon = {
                    tab.options.icon?.let { painter ->
                        Icon(
                            painter = painter,
                            contentDescription = tab.options.title
                        )
                    }
                },
                label = {
                    Text(text = tab.options.title)
                }
            )
        }
    }
}
