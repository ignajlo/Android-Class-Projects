package com.example.db.dao

import com.example.model.Product

interface DAOProduct {
    suspend fun allProducts(): List<Product>
    suspend fun product(id: Int): Product?
    suspend fun addNewProduct(name: String, description: String, price: Double, quantity: Int): Product?
    suspend fun editProduct(id: Int, name: String, description: String, price: Double, quantity: Int): Boolean
    suspend fun deleteProduct(id: Int): Boolean
}