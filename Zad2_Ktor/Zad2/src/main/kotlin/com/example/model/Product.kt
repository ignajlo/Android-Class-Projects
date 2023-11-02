package com.example.model

import org.jetbrains.exposed.sql.*
import kotlinx.serialization.Serializable



@Serializable
data class Product(val id: Int, val name: String, val description: String, val price: Double, val quantity: Int)

object Products : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val description = varchar("description", 1024)
    val price = double("price")
    val quantity = integer("quantity")

    override val primaryKey = PrimaryKey(id)
}