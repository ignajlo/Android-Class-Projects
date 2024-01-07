package com.example.zad8

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zad8.presentation.profile.ProfileScreen
import com.google.android.gms.auth.api.identity.Identity
import com.example.zad8.presentation.sign_in.SignInViewModel
import com.example.zad8.ui.theme.Zad8Theme
import kotlinx.coroutines.launch
import com.example.zad8.presentation.sign_in.GoogleAuthClient
import com.example.zad8.presentation.sign_in.SignInScreen
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.example.zad8.R
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    private val callbackManager by lazy {
        CallbackManager.Factory.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.setApplicationId(getString(R.string.facebook_application_id))
        FacebookSdk.setClientToken(getString(R.string.facebook_client_token))
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)



        setContent {
            Zad8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in"){
                        composable("sign_in"){
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if(googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("profile")
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = {
                                    result ->
                                    if(result.resultCode==RESULT_OK){
                                        lifecycleScope.launch{
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if(state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("profile")
                                    viewModel.resetState()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onGoogleSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        signInIntentSender?.let {
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender
                                                ).build()
                                            )
                                        }
                                    }
                                },
                                onEmailSignInClick = { email, password ->
                                    viewModel.signInWithEmailPassword(email, password)
                                },
                                onSignUpClick = { email, password ->
                                    viewModel.signUpWithEmailPassword(email, password)
                                },
                                onFacebookSignInClick = {
                                    val facebookCallback = object : FacebookCallback<LoginResult> {
                                        override fun onSuccess(result: LoginResult) {
                                            Log.d("FacebookLogin", "Facebook Access Token: ${result.accessToken.token}")

                                            lifecycleScope.launch {
                                                val signInResult = googleAuthUiClient.signInWithFacebook(result.accessToken)
                                                if (signInResult.data != null) {
                                                    Toast.makeText(
                                                        applicationContext,
                                                        "Facebook Sign In",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                    navController.navigate("profile")
                                                }
                                            }
                                        }

                                        override fun onCancel() {
                                            // Handle cancellation
                                        }

                                        override fun onError(error: FacebookException) {
                                            Log.e("FacebookLogin", "Facebook login error: ${error.message}")

                                        }
                                    }

                                    LoginManager.getInstance().registerCallback(callbackManager, facebookCallback)
                                    LoginManager.getInstance().logInWithReadPermissions(this@MainActivity, listOf("email"))



                                    Toast.makeText(
                                        applicationContext,
                                        "Facebook Sign In",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            )
                        }

                        composable("profile") {
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed out",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}


