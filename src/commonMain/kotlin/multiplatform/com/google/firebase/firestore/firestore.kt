package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.Deferred
import multiplatform.com.google.firebase.FirebaseException
import kotlin.reflect.KClass

expect class FirebaseFirestore {
    companion object {
        fun getInstance(): FirebaseFirestore
    }
    fun getFirestoreSettings(): FirebaseFirestoreSettings
    fun setFirestoreSettings(settings: FirebaseFirestoreSettings)
    fun collection(collectionPath: String): CollectionReference
}

expect class FirebaseFirestoreSettings {
    class Builder constructor() {
        constructor(settings: FirebaseFirestoreSettings)
        fun setPersistenceEnabled(enabled: Boolean): FirebaseFirestoreSettings.Builder
        fun setTimestampsInSnapshotsEnabled(enabled: Boolean): FirebaseFirestoreSettings.Builder
        fun build(): FirebaseFirestoreSettings
    }
}

expect open class Query {
    fun whereGreaterThan(field: String, value: Any): Query
    fun whereGreaterThan(path: FieldPath, value: Any): Query
    fun get(): Deferred<QuerySnapshot>
    fun addSnapshotListener(listener: EventListener<QuerySnapshot>): ListenerRegistration
    fun addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration
}

expect class CollectionReference : Query {
}

expect class FirebaseFirestoreException : FirebaseException

expect class QuerySnapshot {
    val documents: List<DocumentSnapshot>
}

expect class DocumentSnapshot {
    fun <T: Any> toObject(valueType: KClass<T>): T
}

expect interface ListenerRegistration {
    fun remove()
}

expect interface EventListener<T> {
    fun onEvent(snapshot: T?, exception: FirebaseFirestoreException?)
}

expect class FieldPath {
    companion object {
        fun of(vararg fieldNames: String): FieldPath
    }
}