package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.await
import kotlin.js.Promise

@JsModule("firebase")
external val firebase: dynamic
@JsModule("firebase/auth")
external val auth: dynamic

actual fun getFirebaseAuth() = firebase.auth()

actual interface AuthStateListener {
    actual fun onAuthStateChanged(auth: FirebaseAuth)
}

@JsModule("firebase/auth")
open external class Auth {
    val currentUser: FirebaseUser?
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
}

actual abstract class FirebaseUser : User()

actual val FirebaseUser.uid: String
    get() = uid

actual suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String) = (firebase.auth().signInWithCustomToken(token) as Promise<AuthResult>).await()

actual suspend fun FirebaseAuth.awaitSignInAnonymously() = (firebase.auth().signInAnonymously() as Promise<AuthResult>).await()

actual val FirebaseUser.isAnonymous: Boolean
    get() = isAnonymous

actual suspend fun FirebaseUser.awaitDelete() = firebase.auth().currentUser?.delete()

actual suspend fun FirebaseUser.awaitReload() = firebase.auth().currentUser?.reload()

actual fun FirebaseAuth.addAuthStateListener(listener: AuthStateListener)  = firebase.auth().onAuthStateChanged(listener)

actual fun FirebaseAuth.removeAuthStateListener(listener: AuthStateListener) {
}

actual fun FirebaseAuth.signOut() = firebase.auth().signOut()