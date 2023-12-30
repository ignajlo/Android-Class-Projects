package com.example.zad7.models

data class Product (
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int,
    val categoryId: Long?
)