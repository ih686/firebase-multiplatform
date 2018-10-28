package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.Deferred

expect fun getFirebaseFunctions(): FirebaseFunctions

expect class FirebaseFunctions {
    fun getHttpsCallable(name: String): HttpsCallableReference
}

expect class HttpsCallableReference

expect fun HttpsCallableReference.callAsync(data: Any?): Deferred<HttpsCallableResult>
expect fun HttpsCallableReference.callAsync(): Deferred<HttpsCallableResult>


expect class HttpsCallableResult

expect val HttpsCallableResult.data: Any
