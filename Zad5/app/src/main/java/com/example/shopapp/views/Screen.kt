package com.example.shopapp.views

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.shopapp.R

const val ITEM_ID_KEY = "id"
sealed class Screen(val route: String, val icon: ImageVector, @StringRes val title: Int) {

    object Items : Screen("products", Icons.AutoMirrored.Filled.List, R.string.items)
    object Cart : Screen("cart", Icons.Filled.ShoppingCart, R.string.cart)
    object ItemsDetails : Screen("product/{$ITEM_ID_KEY}", Icons.Default.Info, R.string.item_details)
    object ItemInCartDetails : Screen("cart/{$ITEM_ID_KEY}", Icons.Default.Info, R.string.item_details )

}