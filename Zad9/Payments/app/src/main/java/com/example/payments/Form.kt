package com.example.payments

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.payments.ui.theme.PaymentsOnPrimary
import com.example.payments.ui.theme.PaymentsPrimary
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form() {

    val context = LocalContext.current

    var cardNumber by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var cvvNumber by remember { mutableStateOf("") }

    var errors by remember { mutableStateOf(mapOf<String, Boolean>()) }

    var paymentResult by remember { mutableStateOf<Response<Void>?>(null) }
    var triggerPayment by remember { mutableStateOf(false) }

    LaunchedEffect(triggerPayment) {
        if (triggerPayment) {
            val paymentDetails = PaymentModel(
                cardNumber = cardNumber,
                ownerName = ownerName,
                expirationDate = expirationDate,
                cvvNumber = cvvNumber
            )

            val paymentRepository = PaymentRep()
            try {
                paymentResult = paymentRepository.processPayment(paymentDetails)
            } catch (e: Exception) {
                Log.d("Form", "Error processing payment", e)
            }

            triggerPayment = false

            showToastMessage(context, paymentResult?.isSuccessful == true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        @Composable
        fun OutlinedTextFieldWithBackground(
            value: String,
            onValueChange: (String) -> Unit,
            label: @Composable () -> Unit,
            fieldName: String,
            placeholder: @Composable (() -> Unit)? = null
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    onValueChange(it)
                    errors = errors.toMutableMap().apply {
                        this[fieldName] = !validateField(fieldName, it)
                    }
                },
                label = label,
                isError = errors[fieldName] ?: false,
                placeholder = placeholder,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .background(
                        PaymentsPrimary.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(16.dp)
            )
        }

        OutlinedTextFieldWithBackground(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            label = { Text("Card number") },
            fieldName = "cardNumber",
            placeholder = { Text("1234 5678 9012 3456") }
        )
        OutlinedTextFieldWithBackground(
            value = ownerName,
            onValueChange = { ownerName = it },
            label = { Text("Cardholder's name") },
            fieldName = "cardholdersName",
            placeholder = { Text("John Doe") }
        )

        OutlinedTextFieldWithBackground(
            value = expirationDate,
            onValueChange = { expirationDate = it },
            label = { Text("Expiration date") },
            fieldName = "expirationDate",
            placeholder = { Text("MM/YY") }
        )

        OutlinedTextFieldWithBackground(
            value = cvvNumber,
            onValueChange = { cvvNumber = it },
            label = { Text("CVV") },
            fieldName = "cvv",
            placeholder = { Text("123") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                errors = mapOf(
                    "cardNumber" to !isValidCardNumber(cardNumber),
                    "cardOwnerName" to !isValidOwnerName(ownerName),
                    "expirationDate" to !isValidExpirationDate(expirationDate),
                    "cvv" to !isValidCvv(cvvNumber)
                )

                if (!errors.values.any { it }) {
                    triggerPayment = true
                }
            },
            modifier = Modifier.background(PaymentsPrimary, shape = MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Text("Pay")
        }
    }
}

fun showToastMessage(context: Context, success: Boolean) {
    val message = if (success) "Payment success" else "Payment failure"
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun validateField(fieldName: String, value: String): Boolean {
    return when (fieldName) {
        "cardNumber" -> isValidCardNumber(value)
        "cardOwnerName" -> isValidOwnerName(value)
        "expirationDate" -> isValidExpirationDate(value)
        "cvv" -> isValidCvv(value)
        else -> false
    }
}

fun isValidCardNumber(cardNumber: String): Boolean = cardNumber.length == 16

fun isValidOwnerName(name: String): Boolean = name.isNotEmpty()

fun isValidExpirationDate(expiryDate: String): Boolean {
    val dateFormat = SimpleDateFormat("MM/yy", Locale.getDefault())
    dateFormat.isLenient = false

    return try {
        val expiration = dateFormat.parse(expiryDate) ?: return false
        val currentDate = Calendar.getInstance().time
        expiration.after(currentDate)
    } catch (e: Exception) {
        false
    }
}

fun isValidCvv(cvv: String): Boolean = cvv.length == 3

