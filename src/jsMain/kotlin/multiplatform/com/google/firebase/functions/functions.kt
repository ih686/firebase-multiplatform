package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.await
import multiplatform.com.google.firebase.FirebaseException
import multiplatform.com.google.firebase.firebase
import multiplatform.com.google.firebase.fromJson
import multiplatform.com.google.firebase.toJson
import kotlin.js.Promise
import kotlin.js.json


actual fun getFirebaseFunctions() = firebase.functions()

actual typealias FirebaseFunctions = firebase.functions.Functions
actual typealias HttpsCallableResult = firebase.functions.HttpsCallableResult
actual typealias HttpsCallableReference = firebase.functions.HttpsCallable

actual val HttpsCallableResult.data: Any
    get() = fromJson(asDynamic().data as Any)!!

actual suspend fun HttpsCallableReference.awaitCall(data: Any?) = translateException { this.asDynamic()(toJson(data)).unsafeCast<Promise<HttpsCallableResult>>().await() }

actual suspend fun HttpsCallableReference.awaitCall() = translateException { this.asDynamic()().unsafeCast<Promise<HttpsCallableResult>>().await() }

actual fun FirebaseFunctions.getHttpsCallable(name: String, timeout: Long?) = httpsCallable(name, timeout?.let { json("timeout" to timeout.toDouble()) })

actual open class FirebaseFunctionsException(code: String, message: String): FirebaseException(Error("${message} - ${code}"))

internal suspend fun <T, R> T.translateException(function: suspend T.() -> R): R {
    var exception: Exception?

    try {
        return function()
    } catch(e: dynamic) {
        if(e.code !== undefined) {
            throw when(e.code) {

                "cancelled" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "unknown" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "invalid-argument" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "deadline-exceeded" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "not-found" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "already-exists" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "permission-denied" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "unauthenticated" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "resource-exhausted" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "failed-precondition" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "aborted" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "out-of-range" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "unimplemented" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "internal" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "unavailable" -> FirebaseFunctionsException(e.message as String, e.code as String)
                "data-loss" -> FirebaseFunctionsException(e.message as String, e.code as String)

                else -> FirebaseException(e)
            }
        } else {
            exception = FirebaseException(e)
        }
    }


    throw exception!!
}