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

actual fun HttpsCallableReference.callAsync(data: Any?): Deferred<HttpsCallableResult> {
    TODO("not implemented")
}

actual class HttpsCallableResult

actual val HttpsCallableResult.data: Any
    get() = TODO("not implemented")

actual fun HttpsCallableReference.callAsync(): Deferred<HttpsCallableResult> {
    TODO("not implemented")
}