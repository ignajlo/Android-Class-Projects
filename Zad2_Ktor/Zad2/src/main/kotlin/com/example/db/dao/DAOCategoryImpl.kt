package com.example.db.dao

import com.example.db.DatabaseFactory.dbQuery
import com.example.model.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOCategoryImpl : DAOCategory {
    private fun resultRowToCategory(row: ResultRow) = Category(
        id = row[Categories.id],
        name = row[Categories.name],
        description = row[Categories.description],
        tag = row[Categories.tag],
        minimalPrice = row[Categories.minimalPrice]
    )


    override suspend fun allCategories(): List<Category> = dbQuery {
        Categories.selectAll().map(::resultRowToCategory)
    }

    override suspend fun category(id: Int): Category? = dbQuery {
        Categories
            .select { Categories.id eq id }
            .map(::resultRowToCategory)
            .singleOrNull()
    }

    override suspend fun addNewCategory(
        name: String,
        description: String,
        tag: String,
        minimalPrice: Double
    ): Category? = dbQuery {
        val insertStatement = Categories.insert {
            it[Categories.name] = name
            it[Categories.description] = description
            it[Categories.tag] = tag
            it[Categories.minimalPrice] = minimalPrice
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCategory)
    }

    override suspend fun editCategory(
        id: Int,
        name: String,
        description: String,
        tag: String,
        minimalPrice: Double
    ): Boolean = dbQuery {
        Categories.update({ Categories.id eq id}) {
            it[Categories.name] = name
            it[Categories.description] = description
            it[Categories.tag] = tag
            it[Categories.minimalPrice] = minimalPrice
            it[Categories.id] = id
        } > 0
    }

    override suspend fun deleteCategory(id: Int): Boolean = dbQuery{
        Categories.deleteWhere{Categories.id eq id} > 0
    }
}

val daoCategory : DAOCategory = DAOCategoryImpl().apply {
    runBlocking {
        if(allCategories().isEmpty()) {
            addNewCategory("Category 1", "Description 1", "Tag 1", 1.0)
            addNewCategory("Category 2", "Description 2", "Tag 2", 2.0)
            addNewCategory("Category 3", "Description 3", "Tag 3", 3.0)
            addNewCategory("Category 4", "Description 4", "Tag 4", 4.0)
            addNewCategory("Category 5", "Description 5", "Tag 5", 5.0)
            addNewCategory("Category 6", "Description 6", "Tag 6", 6.0)
        }
    }
}