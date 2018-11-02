package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.Deferred

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

actual fun FirebaseAuth.signInWithCustomTokenAsync(token: String): Deferred<AuthResult> {
    TODO("not implemented")
}

actual val FirebaseAuth.currentUser: FirebaseUser?
    get() = TODO("not implemented")

actual interface AuthResult

actual val AuthResult.user: FirebaseUser
    get() = TODO("not implemented")

actual abstract class FirebaseUser

actual val FirebaseUser.uid: String
    get() = TODO("not implemented")