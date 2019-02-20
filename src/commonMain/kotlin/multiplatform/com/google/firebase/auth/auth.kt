package multiplatform.com.google.firebase.auth

expect fun getFirebaseAuth(): FirebaseAuth

expect interface AuthStateListener {
    fun onAuthStateChanged(auth: FirebaseAuth)
}

expect class FirebaseAuth {
    fun addAuthStateListener(listener: AuthStateListener)
    fun removeAuthStateListener(listener: AuthStateListener)
    fun signOut()
}

expect suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String): AuthResult

expect suspend fun FirebaseAuth.awaitSignInAnonymously(): AuthResult

expect val FirebaseAuth.currentUser: FirebaseUser?

expect interface AuthResult

expect val AuthResult.user: FirebaseUser

expect abstract class FirebaseUser

expect val FirebaseUser.uid: String

expect val FirebaseUser.isAnonymous: Boolean

expect suspend fun FirebaseUser.awaitDelete()

expect suspend fun FirebaseUser.awaitReload()

