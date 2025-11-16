package com.example.kmpapp.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun SideNavigationRail(
    tabs: List<Tab>,
    modifier: Modifier = Modifier
) {
    val tabNavigator = LocalTabNavigator.current
    
    NavigationRail(modifier = modifier) {
        tabs.forEach { tab ->
            NavigationRailItem(
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
