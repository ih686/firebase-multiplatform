package multiplatform.com.google.firebase.functions

actual fun getFirebaseFunctions(): FirebaseFunctions {
    TODO("not implemented")
}

actual class FirebaseFunctions

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

actual fun FirebaseFunctions.getHttpsCallable(name: String): HttpsCallableReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}