package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.Deferred

expect fun getFirebaseAuth(): FirebaseAuth

expect interface AuthStateListener {
    fun onAuthStateChanged(auth: FirebaseAuth)
}

expect class FirebaseAuth {
    fun addAuthStateListener(listener: AuthStateListener)
    fun removeAuthStateListener(listener: AuthStateListener)
    fun signOut()
}

expect fun FirebaseAuth.signInWithCustomTokenAsync(token: String): Deferred<AuthResult>

expect val FirebaseAuth.currentUser: FirebaseUser?

expect class AuthResult

expect val AuthResult.user: FirebaseUser

expect class FirebaseUser

expect val FirebaseUser.uid: String
