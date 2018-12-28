package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.Deferred

expect fun getFirebaseFunctions(): FirebaseFunctions

expect class FirebaseFunctions {
    fun getHttpsCallable(name: String): HttpsCallableReference
}

expect class HttpsCallableReference

expect suspend fun HttpsCallableReference.awaitCall(data: Any?): HttpsCallableResult
expect suspend fun HttpsCallableReference.awaitCall(): HttpsCallableResult


expect class HttpsCallableResult

expect val HttpsCallableResult.data: Any
