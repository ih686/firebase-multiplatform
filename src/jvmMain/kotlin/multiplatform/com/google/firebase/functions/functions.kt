package multiplatform.com.google.firebase.functions

import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.HttpsCallableReference
import com.google.firebase.functions.HttpsCallableResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import multiplatform.com.google.firebase.await

actual class FirebaseFunctions {

    private val instance = FirebaseFunctions.getInstance()

    actual companion object {
        private val instance = FirebaseFunctions()
        actual fun getInstance() = instance
    }

    actual fun getHttpsCallable(name: String) = HttpsCallableReference(instance.getHttpsCallable(name))

}

actual class HttpsCallableReference(private val instance: HttpsCallableReference) {
    actual fun call(data: Any?) = GlobalScope.async { HttpsCallableResult(instance.call(data).await()) }
    actual fun call() = GlobalScope.async { HttpsCallableResult(instance.call().await()) }
}


actual class HttpsCallableResult(private val instance: HttpsCallableResult) {
    actual val data: Any
        get() = instance.data
}