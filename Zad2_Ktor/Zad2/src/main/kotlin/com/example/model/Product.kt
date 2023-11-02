package com.example.model

import org.jetbrains.exposed.sql.*
import kotlinx.serialization.Serializable



@Serializable
data class Product(val id: Int, val name: String, val description: String, val price: Double, val quantity: Int, val categoryId: Int?)

object Products : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val description = varchar("description", 1024)
    val price = double("price")
    val quantity = integer("quantity")
    val categoryId = integer("category_id").references(Categories.id).nullable() // Foreign key reference to Categories

    override val primaryKey = PrimaryKey(id)
}