package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.Deferred

expect class FirebaseAuth {
    companion object {
        fun getInstance(): FirebaseAuth
    }

    fun signInWithCustomToken(token: String): Deferred<AuthResult>
}

expect interface AuthResult