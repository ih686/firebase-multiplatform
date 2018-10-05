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

actual class FirebaseApp {
    actual companion object {
        actual fun initializeApp(context: Any, options: FirebaseOptions) {
            FirebaseApp.initializeApp(context as Context, options.instance)
        }
    }

}

actual class FirebaseOptions(internal val instance: com.google.firebase.FirebaseOptions) {
    actual class Builder {

        private val instance = com.google.firebase.FirebaseOptions.Builder()

        actual fun setApiKey(apiKey: String): Builder {
            instance.setApiKey(apiKey)
            return this
        }

        actual fun setApplicationId(applicationId: String): Builder {
            instance.setApplicationId(applicationId)
            return this
        }

        actual fun setDatabaseUrl(databaseUrl: String?): Builder {
            instance.setDatabaseUrl(databaseUrl)
            return this
        }

        actual fun setStorageBucket(storageBucket: String?): Builder {
            instance.setStorageBucket(storageBucket)
            return this
        }

        actual fun setProjectId(projectId: String?): Builder {
            instance.setProjectId(projectId)
            return this
        }

        actual fun build(): FirebaseOptions {
            return FirebaseOptions(instance.build())
        }
    }
}