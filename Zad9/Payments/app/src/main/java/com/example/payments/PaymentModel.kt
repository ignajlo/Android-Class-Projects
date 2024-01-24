package com.example.payments

data class PaymentModel(
    val cardNumber: String,
    val ownerName: String,
    val expirationDate: String,
    val cvvNumber: String
)