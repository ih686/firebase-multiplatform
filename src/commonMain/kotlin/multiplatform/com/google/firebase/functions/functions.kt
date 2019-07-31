package multiplatform.com.google.firebase.functions

expect fun getFirebaseFunctions(): FirebaseFunctions

expect class FirebaseFunctions
expect fun FirebaseFunctions.getHttpsCallable(name: String, timeout: Long? = null): HttpsCallableReference

expect class HttpsCallableReference

expect suspend fun HttpsCallableReference.awaitCall(data: Any?): HttpsCallableResult
expect suspend fun HttpsCallableReference.awaitCall(): HttpsCallableResult

expect class HttpsCallableResult

expect val HttpsCallableResult.data: Any
