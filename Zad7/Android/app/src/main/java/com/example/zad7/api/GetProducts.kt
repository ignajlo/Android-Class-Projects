package com.example.zad7.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import com.example.zad7.models.Category
import com.example.zad7.models.Product
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class GetProducts {
    private val client = HttpClient()

    suspend fun getProducts(): List<Product> {
        //val response = client.get("http://<your.ip.address>:8080/products")
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Product>> = moshi.adapter(
            Types.newParameterizedType(List::class.java, Product::class.java)
        )
        val products = adapter.fromJson(response.bodyAsText())
        Log.d("getProducts", products.toString())
        return products ?: emptyList()
    }


}