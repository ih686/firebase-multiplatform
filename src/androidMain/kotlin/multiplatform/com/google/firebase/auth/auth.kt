package multiplatform.com.google.firebase.auth

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.tasks.await

actual fun getFirebaseAuth() = FirebaseAuth.getInstance()

actual typealias FirebaseAuth = com.google.firebase.auth.FirebaseAuth

actual val FirebaseAuth.currentUser: FirebaseUser?
    get() = currentUser?.let { FirebaseUser(it) }

actual suspend fun FirebaseAuth.awaitSignInWithCustomToken(token: String) = signInWithCustomToken(token).await()

actual typealias AuthStateListener = com.google.firebase.auth.FirebaseAuth.AuthStateListener

actual typealias AuthResult = com.google.firebase.auth.AuthResult

actual val AuthResult.user: FirebaseUser
    get() = FirebaseUser(user)

actual class FirebaseUser internal constructor(private val user: com.google.firebase.auth.FirebaseUser) : com.google.firebase.auth.FirebaseUser() {
    override val isAnonymous = user.isAnonymous
    override val uid = user.uid
    override fun delete() = user.delete()
    override fun reload() = user.reload()
}

actual val FirebaseUser.uid: String
    get() = uid

actual suspend fun FirebaseAuth.awaitSignInAnonymously() = signInAnonymously().await()

actual val FirebaseUser.isAnonymous: Boolean
    get() = isAnonymous

actual suspend fun FirebaseUser.awaitDelete() = delete().await().run { Unit }

actual suspend fun FirebaseUser.awaitReload() = reload().await().run { Unit }

actual fun FirebaseAuth.addAuthStateListener(listener: AuthStateListener) {
}

actual fun FirebaseAuth.removeAuthStateListener(listener: AuthStateListener) {
}

actual fun FirebaseAuth.signOut() {
}