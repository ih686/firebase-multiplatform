package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.Deferred

actual fun getFirebaseFunctions(): FirebaseFunctions {
    TODO("not implemented")
}

actual class FirebaseFunctions {
    actual fun getHttpsCallable(name: String): HttpsCallableReference {
        TODO("not implemented")
    }
}

actual class HttpsCallableReference


actual class HttpsCallableResult

actual val HttpsCallableResult.data: Any
    get() = TODO("not implemented")

actual suspend fun HttpsCallableReference.awaitCall(data: Any?): HttpsCallableResult {
    TODO("not implemented")
}

actual suspend fun HttpsCallableReference.awaitCall(): HttpsCallableResult {
    TODO("not implemented")
}