package multiplatform.com.google.firebase.auth

@JsModule("firebase")
external val firebase: dynamic
@JsModule("firebase/auth")
external val auth: dynamic

actual class FirebaseAuth {

    private val instance = firebase.auth()

    actual companion object {
        private val instance = FirebaseAuth()
        actual fun getInstance() = instance
    }

    actual fun signInWithCustomToken(token: String) = instance.signInWithCustomToken(token).asDeferred()

}

actual external interface AuthResult