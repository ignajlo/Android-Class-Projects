package com.example.payments.mockapi

import com.example.payments.PaymentModel
import retrofit2.Response
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MockApiService : ApiService {

    override suspend fun processPayment(paymentModel: PaymentModel): Response<Void> {
        // Simulate network delay
        delay(500)

        return when {
               arePaymentDetailsValid(paymentModel) -> {
                    Response.success(null)
                }
            else -> {
                Response.error(400, okhttp3.ResponseBody.create(null, "Invalid card number"))
            }
        }
    }

    private fun arePaymentDetailsValid(paymentModel: PaymentModel): Boolean {
        return isCardNumberValid(paymentModel.cardNumber) &&
                isCvvValid(paymentModel.cvvNumber) &&
                isExpirationDateValid(paymentModel.expirationDate)
    }

    private fun isCardNumberValid(cardNumber: String): Boolean {
        return cardNumber.length == 16 && !cardNumber.startsWith("0")
    }

    private fun isCvvValid(cvv: String): Boolean {
        return cvv.length == 3
    }

    private fun isExpirationDateValid(expirationDate: String): Boolean {
        val dateFormat = SimpleDateFormat("MM/yy", Locale.getDefault())
        dateFormat.isLenient = false

        return try {
            val expiration = dateFormat.parse(expirationDate) ?: return false
            val currentDate = Calendar.getInstance().time
            expiration.after(currentDate)
        } catch (e: Exception) {
            false
        }
    }
}
