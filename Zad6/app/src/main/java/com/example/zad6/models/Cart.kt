package com.example.zad6.models

data class Cart(
    var id: Long,
    var products: List<Long> = listOf(),
    var userId: Long = 0
)
