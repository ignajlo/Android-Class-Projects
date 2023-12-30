package com.example.zad7.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import com.example.zad7.models.Category
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GetCategories {
    private val client = HttpClient()

    suspend fun getCategories(): List<Category> {
        //val response = client.get("http://<your.ip.address>:8080/categories")
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Category>> = moshi.adapter(
            Types.newParameterizedType(List::class.java, Category::class.java)
        )
        val categories = adapter.fromJson(response.bodyAsText())
        Log.d("getCategories", categories.toString())
        return categories ?: emptyList()
    }


}