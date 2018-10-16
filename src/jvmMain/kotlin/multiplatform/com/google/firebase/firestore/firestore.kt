package multiplatform.com.google.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import multiplatform.com.google.firebase.await
import kotlin.reflect.KClass

actual class FirebaseFirestore {

    private val instance = FirebaseFirestore.getInstance()

    actual companion object {
        private val instance = FirebaseFirestore()
        actual fun getInstance() = instance
    }

    actual fun collection(collectionPath: String) = CollectionReference(instance.collection(collectionPath))
}

actual class CollectionReference(instance: CollectionReference) : multiplatform.com.google.firebase.firestore.Query(instance) {
}

actual typealias FirebaseFirestoreException = com.google.firebase.firestore.FirebaseFirestoreException

actual class QuerySnapshot(internal val instance: com.google.firebase.firestore.QuerySnapshot) {
    actual val documents: List<DocumentSnapshot>
        get() = instance.documents.map { DocumentSnapshot(it) }

}

actual class DocumentSnapshot(internal val instance: com.google.firebase.firestore.DocumentSnapshot) {
    actual fun <T : Any> toObject(valueType: KClass<T>) = instance.toObject(valueType.java) as T
}

actual typealias EventListener<T> = com.google.firebase.firestore.EventListener<T>

actual typealias ListenerRegistration = ListenerRegistration

actual open class Query(private val instance: Query) {
    actual fun get() = GlobalScope.async { QuerySnapshot(instance.get().await()) }
    actual fun whereGreaterThan(field: String, value: Any) = Query(instance.whereGreaterThan(field, value))
    actual fun whereGreaterThan(path: FieldPath, value: Any) = Query(instance.whereGreaterThan(path.instance, value))
    actual fun addSnapshotListener(listener: EventListener<QuerySnapshot>) = instance.addSnapshotListener { s, e -> listener.onEvent(s?.let { QuerySnapshot(it) }, e) }
    actual fun addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit) = instance.addSnapshotListener { s, e -> listener(s?.let { QuerySnapshot(it) }, e) }
}


actual class FieldPath(internal val instance: com.google.firebase.firestore.FieldPath) {
    actual companion object {
        actual fun of(vararg fieldNames: String) = multiplatform.com.google.firebase.firestore.FieldPath(com.google.firebase.firestore.FieldPath.of(*fieldNames))
    }
}