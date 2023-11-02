package com.example.model

import org.jetbrains.exposed.sql.*
import kotlinx.serialization.Serializable



@Serializable
data class Category(val id: Int, val name: String, val description: String, val tag: String, val minimalPrice: Double)

object Categories : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val description = varchar("description", 1024)
    val tag = varchar("tag", 128)
    val minimalPrice = double("minimalPrice")

    override val primaryKey = PrimaryKey(id)
}