package multiplatform.com.google.firebase.functions

import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.HttpsCallableReference
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

actual class HttpsCallableReference(private val ref: HttpsCallableReference) {
    actual fun call(data: Any?) = GlobalScope.async { ref.call(data).await() }
    actual fun call() = GlobalScope.async { ref.call().await() }
}


actual typealias HttpsCallableResult = com.google.firebase.functions.HttpsCallableResult