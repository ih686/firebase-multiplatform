package multiplatform.com.google.firebase.functions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.asDeferred

actual fun getFirebaseFunctions() = FirebaseFunctions.getInstance()

actual typealias FirebaseFunctions = com.google.firebase.functions.FirebaseFunctions


actual typealias HttpsCallableReference = com.google.firebase.functions.HttpsCallableReference

actual fun HttpsCallableReference.callAsync(data: Any?) = call(data).asDeferred()
actual fun HttpsCallableReference.callAsync() = call().asDeferred()


actual typealias HttpsCallableResult = com.google.firebase.functions.HttpsCallableResult

actual val HttpsCallableResult.data: Any
        get() = data
