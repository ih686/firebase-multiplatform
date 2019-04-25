package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.await
import kotlin.js.Promise

@JsModule("firebase")
external val firebase: dynamic
@JsModule("firebase/auth")
external val auth: dynamic

actual fun getFirebaseAuth() = firebase.auth() as FirebaseAuth

actual interface AuthStateListener {
    actual fun onAuthStateChanged(auth: FirebaseAuth)
}

@JsModule("firebase/auth")
open external class Auth {
    val currentUser: FirebaseUser?

    fun signInWithCustomToken(token: String): Promise<AuthResult>
    fun signInAnonymously(): Promise<AuthResult>
    fun signOut(): Promise<Unit>

    fun onAuthStateChanged(nextOrObserver: Any): ()->Unit

    var unsubscribe: ()->Unit
}

actual typealias FirebaseAuth = Auth

actual val FirebaseAuth.currentUser: FirebaseUser?
    get() = currentUser?.let{ it }

@JsModule("firebase/auth")
open external class UserCredential {
    val user: User
}

actual interface AuthResult {
    val user: FirebaseUser
}

actual val AuthResult.user: FirebaseUser
    get() = user

@JsModule("firebase/auth")
open external class User {
    val uid: String
    val isAnonymous: Boolean

    fun delete(): Promise<Unit>
    fun reload(): Promise<Unit>
}

actual abstract class FirebaseUser : User()

actual val FirebaseUser.uid: String
    get() = uid

actual suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String) = signInWithCustomToken(token).await()

actual suspend fun FirebaseAuth.awaitSignInAnonymously() = signInAnonymously().await()

actual suspend fun FirebaseAuth.signOut() = signOut().await()

actual val FirebaseUser.isAnonymous: Boolean
    get() = isAnonymous

actual suspend fun FirebaseUser.awaitDelete() = delete().await()

actual suspend fun FirebaseUser.awaitReload() = reload().await()

actual fun FirebaseAuth.addAuthStateListener(listener: AuthStateListener)  =
        onAuthStateChanged(listener)
        .let{ unsubscribe = it }

actual fun FirebaseAuth.removeAuthStateListener(listener: AuthStateListener) = unsubscribe()

