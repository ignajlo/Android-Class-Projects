package com.example.zad6

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.zad6.models.Cart
import com.example.zad6.models.Category
import com.example.zad6.models.Product
import com.example.zad6.models.User
import com.example.zad6.ui.theme.Zad6Theme
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Zad6Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Buttons for retrieving data from Firestore
                        StyledButton(onClick = { getUserData() }, text = "Print User data")
                        Spacer(modifier = Modifier.height(8.dp))
                        StyledButton(onClick = { getCategoryData() }, text = "Print Category data")
                        Spacer(modifier = Modifier.height(8.dp))
                        StyledButton(onClick = { getProductData() }, text = "Print Product data")
                        Spacer(modifier = Modifier.height(8.dp))
                        StyledButton(onClick = { getCartData() }, text = "Print Cart data")
                    }
                }
            }
        }




        // Sample data creation
        val category1 = Category(
            id = 1,
            name = "Games"
        )

        val category2 = Category(
            id = 2,
            name = "Electronics"
        )

        val product1 = Product(
            id = 201,
            name = "Cyberpunk 2077",
            price = 59.99,
            categoryId = category1.id
        )

        val product2 = Product(
            id = 202,
            name = "PlayStation 5",
            price = 499.99,
            categoryId = category2.id
        )

        val user1 = User(
            id = 301,
            email = "example@example.com",
            nickname = "V"
        )

        val user2 = User(
            id = 302,
            email = "another@example.com",
            nickname = "Gamer123"
        )

        val cart1 = Cart(
            id = 1,
            products = listOf(product1.id),
            userId = user1.id
        )

        val cart2 = Cart(
            id = 2,
            products = listOf(product2.id),
            userId = user2.id
        )

// Writing data to Firestore
        addCategoryToFirestore(category1)
        addCategoryToFirestore(category2)
        addProductToFirestore(product1)
        addProductToFirestore(product2)
        addUserToFirestore(user1)
        addUserToFirestore(user2)
        addCartToFirestore(cart1)
        addCartToFirestore(cart2)
    }




    private fun getCategoryData() {
        fetchDataAndShowToast("categories")
    }

    private fun getProductData() {
        fetchDataAndShowToast("products")
    }

    private fun getUserData() {
        fetchDataAndShowToast("users")
    }

    private fun getCartData() {
        fetchDataAndShowToast("carts")
    }

    private fun fetchDataAndShowToast(collection: String) {
        db.collection(collection)
            .get()
            .addOnSuccessListener {  result ->
                for (document in result) {
                    val message = "${document.data}"
                    showToast(message)
                }
            }
            .addOnFailureListener { exception ->
                showToast("Error getting documents: ${exception.message}")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun addCategoryToFirestore(category: Category) {
        db.collection("categories").document(category.id.toString()).set(category)
            .addOnSuccessListener {
                Log.d("Firestore", "Category DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding category document", e)
            }
    }

    private fun addProductToFirestore(product: Product) {
        db.collection("products").document(product.id.toString()).set(product)
            .addOnSuccessListener {
                Log.d("Firestore", "Product DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding product document", e)
            }
    }

    private fun addUserToFirestore(user: User) {
        db.collection("users").document(user.id.toString()).set(user)
            .addOnSuccessListener {
                Log.d("Firestore", "User DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding user document", e)
            }
    }

    private fun addCartToFirestore(cart: Cart) {
        db.collection("carts").document(cart.id.toString()).set(cart)
            .addOnSuccessListener {
                Log.d("Firestore", "Cart DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding cart document", e)
            }
    }
}

@Composable
private fun StyledButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
           // backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}

