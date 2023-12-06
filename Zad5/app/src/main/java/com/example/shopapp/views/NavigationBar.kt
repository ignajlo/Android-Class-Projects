package com.example.shopapp.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavigationBar(navController: NavController) {

    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf(
        Screen.Items,
        Screen.Cart
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEachIndexed { index, screen ->
            BottomNavigationItem(
                icon = {
                    if (index == 0) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    } else {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                    }
                },
                label = { Text(stringResource(id = screen.title)) },
                selected = selectedItem == index,
                onClick = {
                    navController.navigate(screen.route)
                    selectedItem = index
                },

            )
        }
    }
}