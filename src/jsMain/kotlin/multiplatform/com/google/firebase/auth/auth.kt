package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.await
import multiplatform.com.google.firebase.FirebaseException
import multiplatform.com.google.firebase.firebase
import kotlin.js.Promise

actual fun getFirebaseAuth() = firebase.auth()

actual typealias FirebaseAuth = firebase.auth.Auth

actual interface AuthStateListener {
    actual fun onAuthStateChanged(auth: FirebaseAuth)
}

actual val FirebaseAuth.currentUser: FirebaseUser?
    get() = currentUser

actual typealias AuthResult = firebase.auth.AuthResult

actual val AuthResult.user: FirebaseUser
    get() = user


actual typealias FirebaseUser = firebase.user.User

actual val FirebaseUser.uid: String
    get() = uid

actual suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String) = translateException { signInWithCustomToken(token).await() }

actual suspend fun FirebaseAuth.awaitSignInAnonymously() = translateException { Promise.resolve(signInAnonymously()).await() }

actual suspend fun FirebaseAuth.signOut() = translateException { signOut().await() }

actual val FirebaseUser.isAnonymous: Boolean
    get() = isAnonymous

actual suspend fun FirebaseUser.awaitDelete() = translateException { delete().await() }

actual suspend fun FirebaseUser.awaitReload() = translateException { reload().await() }

actual fun FirebaseAuth.addAuthStateListener(listener: AuthStateListener)  =
        onAuthStateChanged { listener.onAuthStateChanged(getFirebaseAuth()) }
                .let { listener.asDynamic().unsubscribe = it }

actual fun FirebaseAuth.removeAuthStateListener(listener: AuthStateListener) = listener.asDynamic().unsubscribe()

actual open class FirebaseAuthException(code: String, message: String): FirebaseException(Error("${message} - ${code}"))
actual open class FirebaseAuthActionCodeException(code: String, message: String): FirebaseAuthException(code, message)
actual open class FirebaseAuthEmailException(code: String, message: String): FirebaseAuthException(code, message)
actual open class FirebaseAuthInvalidCredentialsException(code: String, message: String): FirebaseAuthException(code, message)
actual open class FirebaseAuthInvalidUserException(code: String, message: String): FirebaseAuthException(code, message)
actual open class FirebaseAuthRecentLoginRequiredException(code: String, message: String): FirebaseAuthException(code, message)
actual open class FirebaseAuthUserCollisionException(code: String, message: String): FirebaseAuthException(code, message)
actual open class FirebaseAuthWebException(code: String, message: String): FirebaseAuthException(code, message)

internal suspend fun <T, R> T.translateException(function: suspend T.() -> R): R {
    var exception: Exception?

    try {
        return function()
    } catch(e: dynamic) {
        if(e.code !== undefined) {
            throw when(e.code) {
                "auth/app-deleted" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/app-not-authorized" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/argument-error" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/invalid-api-key" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/invalid-user-token" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                "auth/network-request-failed" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/operation-not-allowed" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/requires-recent-login" -> FirebaseAuthRecentLoginRequiredException(e.code as String, e.message as String)
                "auth/too-many-arguments" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/unauthorized-domain" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/user-disabled" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                "auth/user-token-expired" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                "auth/web-storage-unsupported" -> FirebaseAuthWebException(e.code as String, e.message as String)
                else -> FirebaseException(e)
            }
        } else {
            exception = FirebaseException(e)
        }
    }


    throw exception!!
}