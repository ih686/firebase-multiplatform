package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.Deferred

expect class FirebaseFunctions {
    companion object {
        fun getInstance(): FirebaseFunctions
    }

    fun getHttpsCallable(name: String): HttpsCallableReference
}

expect class HttpsCallableReference {
    fun call(data: Any?): Deferred<HttpsCallableResult>
    fun call(): Deferred<HttpsCallableResult>
}

expect class HttpsCallableResult {
    val data: Any
}
