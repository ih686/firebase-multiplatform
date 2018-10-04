package multiplatform.com.google.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import multiplatform.com.google.firebase.await


actual class FirebaseFirestore {

    private val db = FirebaseFirestore.getInstance()

    actual companion object {
        private val instance = FirebaseFirestore()
        actual fun getInstance() = instance
    }

    actual fun collection(collectionPath: String) = CollectionReference(db.collection(collectionPath))
}

actual class CollectionReference(private val ref: CollectionReference) {
    actual fun get() = GlobalScope.async { ref.get().await() }
}

actual typealias QuerySnapshot = com.google.firebase.firestore.QuerySnapshot