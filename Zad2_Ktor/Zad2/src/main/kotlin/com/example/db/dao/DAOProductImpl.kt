package com.example.db.dao

import com.example.model.Product
import com.example.model.Products
import com.example.db.DatabaseFactory.dbQuery
import com.example.model.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOProductImpl : DAOProduct {

    private fun resultRowToProduct(row: ResultRow) = Product(
        id = row[Products.id],
        name = row[Products.name],
        description = row[Products.description],
        price = row[Products.price],
        quantity = row[Products.quantity],
        categoryId = row[Products.categoryId]
    )

    override suspend fun allProducts(): List<Product> = dbQuery {
        Products.selectAll().map(::resultRowToProduct)
    }

    override suspend fun product(id: Int): Product? = dbQuery {
        Products
            .select { Products.id eq id }
            .map(::resultRowToProduct)
            .singleOrNull()
    }

    override suspend fun addNewProduct(name: String, description: String, price: Double, quantity: Int, categoryId: Int?): Product? = dbQuery {
        val insertStatement = Products.insert {
            it[Products.name] = name
            it[Products.description] = description
            it[Products.price] = price
            it[Products.quantity] = quantity
            it[Products.categoryId] = categoryId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProduct)
    }

    override suspend fun editProduct(
        id: Int,
        name: String,
        description: String,
        price: Double,
        quantity: Int,
        categoryId: Int?
    ): Boolean = dbQuery{
        Products.update({ Products.id eq id}) {
            it[Products.name] = name
            it[Products.description] = description
            it[Products.price] = price
            it[Products.quantity] = quantity
            it[Products.id] = id
            it[Products.categoryId] = categoryId
         } > 0
    }

    override suspend fun deleteProduct(id: Int): Boolean = dbQuery {
        Products.deleteWhere{Products.id eq id} > 0
    }
}

val daoProduct: DAOProduct = DAOProductImpl().apply {
    runBlocking {
        if(allProducts().isEmpty()) {
            addNewProduct("Product 1", "Description 1", 1.0, 1, null)
            addNewProduct("Product 2", "Description 2", 2.0, 2, null)
            addNewProduct("Product 3", "Description 3", 3.0, 3, null)
        }
    }
}