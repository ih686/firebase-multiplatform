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
        instance.signInWithCustomToken(token).await()
    }
}

actual typealias AuthResult = com.google.firebase.auth.AuthResult