package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import multiplatform.com.google.firebase.await

actual fun getFirebaseFunctions() = FirebaseFunctions.getInstance()

actual typealias FirebaseFunctions = com.google.firebase.functions.FirebaseFunctions


actual typealias HttpsCallableReference = com.google.firebase.functions.HttpsCallableReference

actual fun HttpsCallableReference.callAsync(data: Any?) = GlobalScope.async { call(data).await() }
actual fun HttpsCallableReference.callAsync() = GlobalScope.async { call().await() }


actual typealias HttpsCallableResult = com.google.firebase.functions.HttpsCallableResult

actual val HttpsCallableResult.data: Any
        get() = data
