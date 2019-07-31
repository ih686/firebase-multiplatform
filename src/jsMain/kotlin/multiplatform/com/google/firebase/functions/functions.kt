package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.await
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

actual suspend fun HttpsCallableReference.awaitCall(data: Any?) = this.asDynamic()(toJson(data)).unsafeCast<Promise<HttpsCallableResult>>().await()

actual suspend fun HttpsCallableReference.awaitCall() = this.asDynamic()().unsafeCast<Promise<HttpsCallableResult>>().await()

actual fun FirebaseFunctions.getHttpsCallable(name: String, timeout: Long?) = httpsCallable(name, timeout?.let { json("timeout" to timeout.toDouble()) })
