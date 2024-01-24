package com.example.payments

import com.example.payments.mockapi.ApiService
import com.example.payments.mockapi.MockApiService
import retrofit2.Response

class PaymentRep(private val apiService: ApiService = MockApiService()) {
    suspend fun processPayment(paymentModel: PaymentModel): Response<Void> {
        return apiService.processPayment(paymentModel)
    }

}