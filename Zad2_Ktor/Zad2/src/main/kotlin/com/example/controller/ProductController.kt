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

object ProductController {

    private val json = Json

    suspend fun getAllProducts(call: ApplicationCall) {
        val products = daoProduct.allProducts()
        val jsonProducts = json.encodeToString(products)
        call.respondText(jsonProducts, ContentType.Application.Json, HttpStatusCode.OK)
    }

    suspend fun addProduct(call: ApplicationCall) {
        val post = call.receiveParameters()
        val name = post["name"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: name")
        val description = post["description"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: description")
        val price = post["price"]?.toDoubleOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: price")
        val quantity = post["quantity"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: quantity")
        val categoryId = post["categoryId"]?.toIntOrNull()
        val product = daoProduct.addNewProduct(name, description, price, quantity, categoryId)
        if (product == null) {
            call.respond(HttpStatusCode.InternalServerError, "Error adding product")
        } else {
            call.respondRedirect("/products/${product.id}")
        }
    }

    suspend fun getProduct(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: id")
        val product = daoProduct.product(id)
        if (product == null) {
            call.respond(HttpStatusCode.NotFound, "Product not found")
        } else {
            val jsonProduct = json.encodeToString(product)
            call.respondText(jsonProduct, ContentType.Application.Json, HttpStatusCode.OK)
        }
    }

    suspend fun editProduct(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: id")
        val post = call.receiveParameters()
        val name = post["name"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: name")
        val description = post["description"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: description")
        val price = post["price"]?.toDoubleOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: price")
        val quantity = post["quantity"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: quantity")
        val categoryId = post["categoryId"]?.toIntOrNull()
        val success = daoProduct.editProduct(id, name, description, price, quantity, categoryId)
        if (!success) {
            call.respond(HttpStatusCode.NotFound, "Product not found")
        } else {
            call.respondRedirect("/products/$id")
        }
    }

    suspend fun deleteProduct(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest, "Missing field: id")
        val success = daoProduct.deleteProduct(id)
        if (!success) {
            call.respond(HttpStatusCode.NotFound, "Product not found")
        } else {
            call.respondRedirect("/products")
        }
    }
}