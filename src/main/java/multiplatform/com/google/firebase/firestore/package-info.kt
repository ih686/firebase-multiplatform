package multiplatform.com.google.firebase.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <T> Task<T>.await(): T = suspendCoroutine { continuation ->
    addOnCompleteListener { task ->
        if (task.isSuccessful) {
            continuation.resume(task.result)
        } else {
            continuation.resumeWithException(task.exception!!)
        }
    }
}

actual class FirebaseFirestore {

    private val db = FirebaseFirestore.getInstance()

    actual companion object {
        private val instance = FirebaseFirestore()
        actual fun getInstance() = instance
    }

    actual fun collection(collectionPath: String) = CollectionReference(db.collection(collectionPath))
}

actual class CollectionReference(private val ref: CollectionReference) {
    actual suspend fun get(): QuerySnapshot = ref.get().await()
}

actual typealias QuerySnapshot = com.google.firebase.firestore.QuerySnapshot