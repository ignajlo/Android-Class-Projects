package com.example.plugins

import com.example.controller.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        route("/products") {
            get {
                ProductController.getAllProducts(call)
            }
            post {
                ProductController.addProduct(call)
            }
            get("{id}") {
                ProductController.getProduct(call)
            }
            get("{id}/edit") {
                ProductController.editProduct(call)
            }
            post("{id}") {
                ProductController.deleteProduct(call)
            }
        }

        route("/categories") {
            get {
                CategoryController.getAllCategories(call)
            }
            post {
                CategoryController.addCategory(call)
            }
            get("{id}") {
                CategoryController.getCategory(call)
            }
            get("{id}/edit") {
                CategoryController.editCategory(call)
            }
            post("{id}") {
                CategoryController.deleteCategory(call)
            }
        }
    }
}
