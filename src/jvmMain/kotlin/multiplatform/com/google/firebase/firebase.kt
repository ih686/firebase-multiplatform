package multiplatform.com.google.firebase

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <T> Task<T>.await(): T = suspendCoroutine { continuation ->
    addOnCompleteListener { task ->
        if (task.isSuccessful) {
            continuation.resume(task.result!!)
        } else {
            continuation.resumeWithException(task.exception!!)
        }
    }
}

actual typealias FirebaseException = com.google.firebase.FirebaseException

actual fun initializeFirebaseApp(context: Any, options: FirebaseOptions) =
    FirebaseApp.initializeApp(context as Context, options)

actual typealias FirebaseApp = com.google.firebase.FirebaseApp

actual typealias FirebaseOptions = com.google.firebase.FirebaseOptions


actual typealias FirebaseOptionsBuilder = com.google.firebase.FirebaseOptions.Builder