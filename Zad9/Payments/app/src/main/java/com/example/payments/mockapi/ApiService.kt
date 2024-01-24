package com.example.payments.mockapi

import com.example.payments.PaymentModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("processPayment")
    suspend fun processPayment(@Body paymentModel: PaymentModel): Response<Void>
}