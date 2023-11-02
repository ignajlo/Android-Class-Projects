package com.example.controller

import com.example.db.dao.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.util.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import com.example.model.Product
import com.example.controller.ProductController

object CategoryController {
    private val json = Json

    suspend fun getAllCategories(call: ApplicationCall) {
        val categories = daoCategory.allCategories()
        val jsonCategories = json.encodeToString(categories)
        call.respondText(jsonCategories, ContentType.Application.Json, HttpStatusCode.OK)
    }

    suspend fun addCategory(call: ApplicationCall) {
        val post = call.receiveParameters()
        val name = post["name"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: name")
        val description = post["description"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: description")
        val tag = post["tag"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: tag")
        val minimalPrice = post["minimalPrice"]?.toDoubleOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: minimalPrice")
        val category = daoCategory.addNewCategory(name, description, tag, minimalPrice)
        if (category == null) {
            call.respond(HttpStatusCode.InternalServerError, "Error adding category")
        } else {
            call.respondRedirect("/categories/${category.id}")
        }
    }

    suspend fun getCategory(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: id")
        val category = daoCategory.category(id)
        if (category == null) {
            call.respond(HttpStatusCode.NotFound, "Category not found")
        } else {
            val jsonCategory = json.encodeToString(category)
            call.respondText(jsonCategory, ContentType.Application.Json, HttpStatusCode.OK)
        }
    }

    suspend fun editCategory(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: id")
        val post = call.receiveParameters()
        val name = post["name"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: name")
        val description = post["description"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: description")
        val tag = post["tag"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: tag")
        val minimalPrice = post["minimalPrice"]?.toDoubleOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: minimalPrice")
        val success = daoCategory.editCategory(id, name, description, tag, minimalPrice)
        if (!success) {
            call.respond(HttpStatusCode.NotFound, "Category not found")
        } else {
            call.respondRedirect("/categories/$id")
        }
    }

    suspend fun deleteCategory(call: ApplicationCall) {
        val id =
            call.parameters["id"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: id")
        val success = daoCategory.deleteCategory(id)
        if (!success) {
            call.respond(HttpStatusCode.NotFound, "Category not found")
        } else {
            call.respondRedirect("/categories")
        }
    }
}