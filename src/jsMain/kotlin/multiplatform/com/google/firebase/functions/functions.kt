package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.await
import multiplatform.com.google.firebase.App
import kotlin.js.Promise

@JsModule("firebase")
external val firebase: dynamic

@JsModule("firebase/functions")
external fun functions(app: App? = definedExternally): Functions

actual fun getFirebaseFunctions() = functions() as FirebaseFunctions

@JsModule("firebase/functions")
open external class Functions {
    fun httpsCallable(name: String) : HttpsCallable
}

actual typealias FirebaseFunctions = Functions

@JsModule("firebase/functions")
open external class HttpsCallable {
    fun __call(data: Any? = definedExternally): Promise<HttpsCallableResult>
}

actual typealias HttpsCallableReference = HttpsCallable

actual class HttpsCallableResult

actual val HttpsCallableResult.data: Any
    get() = data?.let{ it }

actual suspend fun HttpsCallableReference.awaitCall(data: Any?) = __call(data).await()

actual suspend fun HttpsCallableReference.awaitCall() = __call().await()

actual fun FirebaseFunctions.getHttpsCallable(name: String) = httpsCallable(name)