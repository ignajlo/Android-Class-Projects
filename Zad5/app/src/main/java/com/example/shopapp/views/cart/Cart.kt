package com.example.shopapp.views.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopapp.views.Screen
import org.koin.compose.koinInject
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.example.shopapp.db.Item
import com.example.shopapp.views.ITEM_ID_KEY
import kotlinx.coroutines.launch

@Composable
fun Cart(navController: NavController) {

    val viewModel: CartViewModel = koinInject()
    val items by viewModel.items.collectAsState()

    LazyColumn(Modifier.padding(8.dp)) {
        items(items) { item ->
            Divider(modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), thickness = 2.dp)
            CartItem(item, navController, viewModel)
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItem(item: Item, navController: NavController, viewModel: CartViewModel) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate(
                    Screen.ItemInCartDetails.route.replace(
                        "{$ITEM_ID_KEY}",
                        item.id.toString()
                    )
                )
            }
    ) {
        Box(
            Modifier.padding(10.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = item.name,
                    fontSize = 26.sp,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text("Quantity: " + item.addedQuantity, fontSize = 18.sp)

                }

            }


        }
    }
}

