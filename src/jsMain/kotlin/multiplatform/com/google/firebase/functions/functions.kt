package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.await
import multiplatform.com.google.firebase.firebase
import multiplatform.com.google.firebase.fromJson
import multiplatform.com.google.firebase.toJson


actual fun getFirebaseFunctions() = firebase.functions()

actual typealias FirebaseFunctions = firebase.functions.Functions
actual typealias HttpsCallableResult = firebase.functions.HttpsCallableResult
actual typealias HttpsCallableReference = firebase.functions.HttpsCallable

actual val HttpsCallableResult.data: Any
    get() = fromJson(asDynamic().data as Any)!!

actual suspend fun HttpsCallableReference.awaitCall(data: Any?) = __call(toJson(data)).await()

actual suspend fun HttpsCallableReference.awaitCall() = __call().await()

actual fun FirebaseFunctions.getHttpsCallable(name: String) = httpsCallable(name)