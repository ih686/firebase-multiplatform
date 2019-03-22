package multiplatform.com.google.firebase.functions

@JsModule("firebase")
external val firebase: dynamic
@JsModule("firebase/functions")
external val functions: dynamic


//actual class FirebaseFunctions {
//
//    private val instance = firebase.functions()
//
//    actual companion object {
//        private val instance = FirebaseFunctions()
//        actual fun getInstance() = instance
//    }
//
//    actual fun getHttpsCallable(name: String) = HttpsCallableReference(instance.httpsCallable(name))
//
//}
//
//actual class HttpsCallableReference(private val ref: dynamic) {
//    actual fun call(data: Any?) = ref.call(data).asDeferred()
//    actual fun call() = ref.call().asDeferred()
//}
//
//actual class HttpsCallableResult {
//    actual val data: Any
//        get() = TODO("not implemented")
//}