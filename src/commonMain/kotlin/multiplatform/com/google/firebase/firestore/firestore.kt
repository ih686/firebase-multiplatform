package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import multiplatform.com.google.firebase.FirebaseException
import kotlin.reflect.KClass

expect fun getFirebaseFirestore(): FirebaseFirestore

expect class FirebaseFirestore {
    fun getFirestoreSettings(): FirebaseFirestoreSettings
    fun setFirestoreSettings(settings: FirebaseFirestoreSettings)
    fun collection(collectionPath: String): CollectionReference
    fun document(documentPath: String): DocumentReference
}

expect class FirebaseFirestoreSettingsBuilder constructor() {
    constructor(settings: FirebaseFirestoreSettings)
    fun setPersistenceEnabled(enabled: Boolean): FirebaseFirestoreSettingsBuilder
    fun setTimestampsInSnapshotsEnabled(enabled: Boolean): FirebaseFirestoreSettingsBuilder
    fun build(): FirebaseFirestoreSettings
}

expect class FirebaseFirestoreSettings

expect open class Query {
    fun whereEqualTo(field: String, value: Any?): Query
    fun whereEqualTo(path: FieldPath, value: Any?): Query
    fun whereGreaterThan(field: String, value: Any): Query
    fun whereGreaterThan(path: FieldPath, value: Any): Query
    fun whereArrayContains(field: String, value: Any): Query
    fun whereArrayContains(path: FieldPath, value: Any): Query
    fun addSnapshotListener(listener: EventListener<QuerySnapshot>): ListenerRegistration
}

expect fun Query.addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration

expect fun Query.getAsync(): Deferred<QuerySnapshot>

expect class DocumentReference {
    fun addSnapshotListener(listener: EventListener<DocumentSnapshot>): ListenerRegistration
}

expect val DocumentReference.id: String

expect fun DocumentReference.addSnapshotListener(listener: (snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration

expect fun DocumentReference.setAsync(data: Map<String, Any>): Job

expect fun DocumentReference.setAsync(pojo: Any): Job

expect fun DocumentReference.setAsync(data: Map<String, Any>, options: SetOptions): Job

expect fun DocumentReference.setAsync(pojo: Any, options: SetOptions): Job

expect fun DocumentReference.deleteAsync(): Job

expect class CollectionReference : Query

expect fun CollectionReference.addAsync(data: Map<String, Any>): Deferred<DocumentReference>

expect fun CollectionReference.addAsync(pojo: Any): Deferred<DocumentReference>

expect class FirebaseFirestoreException : FirebaseException

expect class QuerySnapshot

expect val QuerySnapshot.documents: List<DocumentSnapshot>

expect class DocumentSnapshot {
    fun get(field: String): Any?
    fun getString(field: String): String?
    fun contains(field: String): Boolean
}

expect fun <T: Any> DocumentSnapshot.toObject(valueType: KClass<T>): T
expect val DocumentSnapshot.id: String

expect interface ListenerRegistration {
    fun remove()
}

expect interface EventListener<T> {
    fun onEvent(snapshot: T?, exception: FirebaseFirestoreException?)
}

expect class SetOptions

expect fun mergeSetOptions(): SetOptions

expect fun fieldPathOf(vararg fieldNames: String): FieldPath

expect class FieldPath