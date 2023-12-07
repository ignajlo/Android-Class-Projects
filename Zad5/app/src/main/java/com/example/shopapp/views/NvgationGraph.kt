package com.example.shopapp.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shopapp.views.cart.Cart
import com.example.shopapp.views.items.Items
import com.example.shopapp.views.items.ItemDetails

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = Screen.Items.route, Modifier.padding(paddingValues)) {
        composable(Screen.Items.route) {
            Items(navController)
        }
        composable(Screen.Cart.route) {
            Cart(navController)
        }
        composable(Screen.ItemInCartDetails.route) {
            val id = it.arguments?.getString(ITEM_ID_KEY)?.toLongOrNull()
            ItemDetailsSetUp(Screen.ItemsDetails.route, id, navController)
        }
        composable(Screen.ItemsDetails.route) {
            val id = it.arguments?.getString(ITEM_ID_KEY)?.toLongOrNull()
            ItemDetailsSetUp(Screen.ItemsDetails.route, id, navController)
        }

    }

}

@Composable
fun ItemDetailsSetUp(route : String, id : Long?, navController: NavHostController){
    if (id != null) {
        ItemDetails(id, navController)
    }
}
