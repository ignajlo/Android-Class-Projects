package com.example.zad8.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    state: SignInState,
    onGoogleSignInClick: () -> Unit,
    onEmailSignInClick: (String, String) -> Unit,
    onSignUpClick: (String, String) -> Unit,
    onFacebookSignInClick: () -> Unit
) {
    val context = LocalContext.current


    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    var registerEmailState by remember { mutableStateOf("") }
    var registerPasswordState by remember { mutableStateOf("") }


    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = registerEmailState,
            onValueChange = { registerEmailState = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        // Password input field for registration
        OutlinedTextField(
            value = registerPasswordState,
            onValueChange = { registerPasswordState = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        // Button for user registration
        Button(
            onClick = {
                onSignUpClick(registerEmailState, registerPasswordState)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text("Sign Up")
        }


        // Email input field
        OutlinedTextField(
            value = emailState,
            onValueChange = { emailState = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        // Password input field
        OutlinedTextField(
            value = passwordState,
            onValueChange = { passwordState = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        // Button for email/password sign-in
        Button(
            onClick = {
                onEmailSignInClick(emailState, passwordState)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text("Sign In with Email/Password")
        }

        // Button for Google sign-in
        Button(
            onClick = onGoogleSignInClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "Google Sign in")
        }
        Button(
            onClick = onFacebookSignInClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Sign In with Facebook")
        }
    }
}
