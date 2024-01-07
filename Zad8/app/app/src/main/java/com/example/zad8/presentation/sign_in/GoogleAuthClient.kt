package com.example.zad8.presentation.sign_in

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.example.zad8.R
import com.facebook.AccessToken
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender?{
        val result = try{
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        }catch(e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult{
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run{
                    UserData(
                        userId = uid,
                        username = displayName
                    )
                },
                errorMessage = null
            )
        }catch(e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signInWithFacebook(accessToken: AccessToken): SignInResult {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        return try {

            val user = auth.signInWithCredential(credential).await().user
            Log.d("GoogleAuthClient", "Facebook Sign In Success - User ID: ${user?.uid}, DisplayName: ${user?.displayName}")

            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            Log.e("GoogleAuthClient", "Facebook Sign In Error: ${e.message}", e)

            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        }catch(e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run{
        UserData(
            userId = uid,
            username = email
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest
    {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.default_web_client))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}