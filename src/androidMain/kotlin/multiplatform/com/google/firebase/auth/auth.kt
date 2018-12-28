package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await

actual fun getFirebaseAuth() = FirebaseAuth.getInstance()

actual typealias FirebaseAuth = com.google.firebase.auth.FirebaseAuth

actual val FirebaseAuth.currentUser: FirebaseUser?
    get() = currentUser

actual suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String) = signInWithCustomToken(token).await()

actual typealias AuthStateListener = com.google.firebase.auth.FirebaseAuth.AuthStateListener

actual typealias AuthResult = com.google.firebase.auth.AuthResult

actual val AuthResult.user: FirebaseUser
    get() = user

actual typealias FirebaseUser = com.google.firebase.auth.FirebaseUser

actual val FirebaseUser.uid: String
    get() = uid

