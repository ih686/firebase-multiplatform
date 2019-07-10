package multiplatform.com.google.firebase.auth

import kotlinx.coroutines.await
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

actual suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String) = signInWithCustomToken(token).await()

actual suspend fun FirebaseAuth.awaitSignInAnonymously() = Promise.resolve(signInAnonymously()).await()

actual suspend fun FirebaseAuth.signOut() = signOut().await()

actual val FirebaseUser.isAnonymous: Boolean
    get() = isAnonymous

actual suspend fun FirebaseUser.awaitDelete() = delete().await()

actual suspend fun FirebaseUser.awaitReload() = reload().await()

actual fun FirebaseAuth.addAuthStateListener(listener: AuthStateListener)  =
        onAuthStateChanged { listener.onAuthStateChanged(getFirebaseAuth()) }
                .let { listener.asDynamic().unsubscribe = it }

actual fun FirebaseAuth.removeAuthStateListener(listener: AuthStateListener) = listener.asDynamic().unsubscribe()

