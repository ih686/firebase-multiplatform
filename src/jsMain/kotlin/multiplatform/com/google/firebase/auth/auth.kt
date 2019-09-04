package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.await
import multiplatform.com.google.firebase.FirebaseError
import multiplatform.com.google.firebase.FirebaseException
import multiplatform.com.google.firebase.firebase
import multiplatform.com.google.firebase.runActualWithHandler
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

actual suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String) = runActualWithHandler { signInWithCustomToken(token).await() }

actual suspend fun FirebaseAuth.awaitSignInAnonymously() = runActualWithHandler { Promise.resolve(signInAnonymously()).await() }

actual suspend fun FirebaseAuth.signOut() = runActualWithHandler { signOut().await() }

actual val FirebaseUser.isAnonymous: Boolean
    get() = isAnonymous

actual suspend fun FirebaseUser.awaitDelete() = runActualWithHandler { delete().await() }

actual suspend fun FirebaseUser.awaitReload() = runActualWithHandler { reload().await() }

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
