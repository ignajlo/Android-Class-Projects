package com.example.zad8.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val auth = FirebaseAuth.getInstance()


    fun signInWithEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val result = if (task.isSuccessful) {
                    SignInResult(
                        data = UserData(
                            userId = auth.currentUser?.uid,
                            username = auth.currentUser?.displayName
                        ),
                        errorMessage = null
                    )
                } else {
                    SignInResult(
                        data = null,
                        errorMessage = task.exception?.message
                    )
                }
                onSignInResult(result)
            }
    }

    fun signUpWithEmailPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val result = if (task.isSuccessful) {
                    SignInResult(
                        data = UserData(
                            userId = auth.currentUser?.uid,
                            username = auth.currentUser?.displayName
                        ),
                        errorMessage = null
                    )
                } else {
                    SignInResult(
                        data = null,
                        errorMessage = task.exception?.message
                    )
                }
                onSignInResult(result)
            }
    }


    fun onSignInResult(result: SignInResult){
        _state.update{
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState(){
        _state.update{
            SignInState()
        }
    }
}