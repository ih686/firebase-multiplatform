package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.Deferred

expect class FirebaseAuth {
    companion object {
        fun getInstance(): FirebaseAuth
    }

    val currentUser: FirebaseUser?

    fun signInWithCustomToken(token: String): Deferred<AuthResult>
    fun signOut()
}

expect class AuthResult {
    val user: FirebaseUser
}

expect class FirebaseUser {
    val uid: String
}