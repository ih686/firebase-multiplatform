package multiplatform.com.google.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import multiplatform.com.google.firebase.await

actual class FirebaseAuth {

    private val instance = FirebaseAuth.getInstance()

    actual companion object {
        private val instance = FirebaseAuth()
        actual fun getInstance() = instance
    }

    actual fun signInWithCustomToken(token: String) = GlobalScope.async {
        AuthResult(instance.signInWithCustomToken(token).await())
    }

    actual fun signOut() {
        instance.signOut()
    }

    actual val currentUser: FirebaseUser?
        get() = instance.currentUser?.let { FirebaseUser(it) }
}

actual class AuthResult(internal val instance: com.google.firebase.auth.AuthResult) {
    actual val user: FirebaseUser
        get() = FirebaseUser(instance.user)
}

actual class FirebaseUser(internal val instance: com.google.firebase.auth.FirebaseUser) {
    actual val uid: String
        get() = instance.uid

}
