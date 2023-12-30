package com.example.zad7.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val tag: String,
    val minimalPrice: Double
)