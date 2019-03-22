package multiplatform.com.google.firebase.auth

actual fun getFirebaseAuth(): FirebaseAuth {
    TODO("not implemented")
}

actual interface AuthStateListener {
    actual fun onAuthStateChanged(auth: FirebaseAuth)
}

actual class FirebaseAuth {
    actual fun addAuthStateListener(listener: AuthStateListener) {}
    actual fun signOut() {}
    actual fun removeAuthStateListener(listener: AuthStateListener) {}
}

actual val FirebaseAuth.currentUser: FirebaseUser?
    get() = TODO("not implemented")

actual interface AuthResult

actual val AuthResult.user: FirebaseUser
    get() = TODO("not implemented")

actual abstract class FirebaseUser

actual val FirebaseUser.uid: String
    get() = TODO("not implemented")

actual suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String): AuthResult {
    TODO("not implemented")
}

actual suspend fun FirebaseAuth.awaitSignInAnonymously(): AuthResult {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual val FirebaseUser.isAnonymous: Boolean
    get() = TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

actual suspend fun FirebaseUser.awaitDelete() {}
actual suspend fun FirebaseUser.awaitReload() {
}