package com.example.shopapp.views.items

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopapp.R
import com.example.shopapp.db.Item
import org.koin.compose.koinInject
import com.example.shopapp.views.Screen

@Composable
fun ItemDetails(itemId: Long, navController: NavController) {

    val viewModel: ItemDetailsViewModel = koinInject()
    val prevRoute: String? = navController.previousBackStackEntry?.destination?.route

    LaunchedEffect(itemId) {
        viewModel.fetchItemDetails(itemId)
    }

    val item = viewModel.item.collectAsState().value
    var quantity by remember { mutableIntStateOf(1) }

    if(prevRoute == Screen.Cart.route){
        LaunchedEffect(item) {
            item?.let {
                quantity = it.addedQuantity
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = item?.name ?: "",
            fontSize = 70.sp,
            modifier = Modifier.padding(bottom = 70.dp).fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(text = "Description:",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 10.dp))
        Text(text = item?.description ?: "",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 10.dp),
            fontStyle = FontStyle.Italic
        )






        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.fillMaxSize()
        ) {

                    Column(horizontalAlignment = Alignment.End) {
                        when (prevRoute) {
                            Screen.Items.route -> {
                                QuantitySelectorRow(
                                    quantity = quantity,
                                    descriptionString = stringResource(R.string.to_add),
                                    onDecreaseClick = { if (quantity > 1) quantity-- },
                                    onIncreaseClick = { quantity++ }
                                )
                            }

                            Screen.Cart.route -> {
                                QuantitySelectorRow(
                                    quantity = quantity,
                                    descriptionString = stringResource(id = R.string.in_cart),
                                    onDecreaseClick = { if (quantity > 0) quantity-- },
                                    onIncreaseClick = { quantity++ }
                                )
                            }
                        }
                        Text(text = "Price: ${item?.price?.times(quantity) ?: 0} PLN",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, end = 5.dp)
                        )
                        AddToCartButton(item, quantity, viewModel, navController, prevRoute)
                    }

        }

    }

    val context = LocalContext.current
}

@Composable
fun QuantitySelectorRow(
    quantity: Int,
    descriptionString: String?,
    onDecreaseClick: () -> Unit,
    onIncreaseClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(descriptionString ?: "", fontSize = 24.sp, fontStyle = FontStyle.Italic)

        IconButton(
            onClick = { onDecreaseClick() },
            modifier = Modifier.size(80.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null, Modifier.size(80.dp))
        }

        Text("$quantity", fontSize = 35.sp)

        IconButton(
            onClick = { onIncreaseClick() },
            modifier = Modifier.size(80.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, Modifier.size(80.dp))
        }
    }
}

@Composable
fun AddToCartButton(
    item: Item?,
    quantity: Int,
    viewModel: ItemDetailsViewModel,
    navController: NavController,
    prevRoute: String?,
) {
    Button(
        onClick = {
            if (item != null) {
                when(prevRoute){
                    Screen.Items.route -> viewModel.addToCart(item, quantity)
                    Screen.Cart.route -> viewModel.setItemQuantity(item, quantity)
                }
                navController.navigate(prevRoute ?: Screen.Items.route)
            }
        },
        modifier = Modifier
    ) {
        Text(
            text = when(prevRoute){
                Screen.Items.route -> stringResource(R.string.add_to_cart)
                Screen.Cart.route -> stringResource(R.string.update_cart)
                else -> ""
            },
            color = Color.White,
            fontSize = 22.sp,
            modifier = Modifier.padding(5.dp)
        )
    }
}