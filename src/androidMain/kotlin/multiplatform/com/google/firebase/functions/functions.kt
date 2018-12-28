package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await

actual fun getFirebaseFunctions() = FirebaseFunctions.getInstance()

actual typealias FirebaseFunctions = com.google.firebase.functions.FirebaseFunctions


actual typealias HttpsCallableReference = com.google.firebase.functions.HttpsCallableReference

actual suspend fun HttpsCallableReference.awaitCall(data: Any?) = call(data).await()
actual suspend fun HttpsCallableReference.awaitCall() = call().await()


actual typealias HttpsCallableResult = com.google.firebase.functions.HttpsCallableResult

actual val HttpsCallableResult.data: Any
        get() = data
