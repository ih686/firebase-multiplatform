package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.Deferred

actual class FirebaseFunctions {
    actual companion object {
        actual fun getInstance(): FirebaseFunctions {
            TODO("not implemented")
        }
    }

    actual fun getHttpsCallable(name: String): HttpsCallableReference {
        TODO("not implemented")
    }

}

actual class HttpsCallableReference {
    actual fun call(data: Any?): Deferred<HttpsCallableResult> {
        TODO("not implemented")
    }

    actual fun call(): Deferred<HttpsCallableResult> {
        TODO("not implemented")
    }
}

actual class HttpsCallableResult