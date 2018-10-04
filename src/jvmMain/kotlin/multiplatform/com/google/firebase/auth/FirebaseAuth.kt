package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.Deferred

actual class FirebaseAuth {
    actual companion object {
        actual fun getInstance(): FirebaseAuth {
            TODO("not implemented")
        }
    }

    actual fun signInWithCustomToken(token: String): Deferred<AuthResult> {
        TODO("not implemented")
    }

}

actual interface AuthResult