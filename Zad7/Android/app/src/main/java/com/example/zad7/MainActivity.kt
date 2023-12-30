package com.example.zad7

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.zad7.ui.theme.Zad7Theme
import com.example.zad7.api.GetCategories
import com.example.zad7.models.Category

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zad7.api.GetProducts
import com.example.zad7.models.Product
import com.example.zad7.ui.view.CategoryListView
import com.example.zad7.ui.view.ProductListView
import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Zad7Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    var categories by remember { mutableStateOf(emptyList<Category>()) }
                    var products by remember { mutableStateOf(emptyList<Product>()) }

                    LaunchedEffect(true) {
                        scope.launch {
                            try {
                                categories = GetCategories().getCategories()
                                products = GetProducts().getProducts()
                                Log.d("MainActivity", categories.toString())
                                Log.d("MainActivity", products.toString())
                            } catch (e: Exception) {
                                Log.e("MainActivity", e.localizedMessage ?: "error")
                                e.localizedMessage ?: "error"
                            }
                        }
                    }

                    LazyColumn {
                        item {
                            Text(
                                text = "Category List",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(16.dp)
                            )
                            CategoryListView(categories)
                        }

                        item {
                            Text(
                                text = "Product List",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(16.dp)
                            )
                            ProductListView(products)
                        }
                    }
                }
            }
        }
    }
}


