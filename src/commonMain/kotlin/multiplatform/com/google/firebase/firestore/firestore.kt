package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.Deferred
import multiplatform.com.google.firebase.FirebaseException
import kotlin.reflect.KClass

expect fun getFirebaseFirestore(): FirebaseFirestore

expect class FirebaseFirestore {
    fun getFirestoreSettings(): FirebaseFirestoreSettings
    fun setFirestoreSettings(settings: FirebaseFirestoreSettings)
    fun collection(collectionPath: String): CollectionReference
    fun document(documentPath: String): DocumentReference
    fun batch(): WriteBatch
}

expect suspend fun <T> FirebaseFirestore.awaitRunTransaction(func: (transaction: Transaction) -> T): T

expect class Transaction {
    fun set(documentRef: DocumentReference, data: Map<String, Any>): Transaction
    fun set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions): Transaction
    fun set(documentRef: DocumentReference, pojo: Any): Transaction
    fun set(documentRef: DocumentReference, pojo: Any, options: SetOptions): Transaction
    fun update(documentRef: DocumentReference, data: Map<String, Any>): Transaction
    fun update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any): Transaction
    fun update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any): Transaction
    fun delete(documentRef: DocumentReference): Transaction
    fun get(documentRef: DocumentReference): DocumentSnapshot
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

expect class WriteBatch {
    fun set(documentRef: DocumentReference, data: Map<String, Any>): WriteBatch
    fun set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions): WriteBatch
    fun set(documentRef: DocumentReference, pojo: Any): WriteBatch
    fun set(documentRef: DocumentReference, pojo: Any, options: SetOptions): WriteBatch
    fun update(documentRef: DocumentReference, data: Map<String, Any>): WriteBatch
    fun update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any): WriteBatch
    fun update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any): WriteBatch
    fun delete(documentRef: DocumentReference): WriteBatch

}

expect suspend fun WriteBatch.awaitCommit()

expect fun Query.addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration

expect suspend fun Query.awaitGet(): QuerySnapshot

expect class DocumentReference {
    fun addSnapshotListener(listener: EventListener<DocumentSnapshot>): ListenerRegistration
}

expect val DocumentReference.id: String

expect fun DocumentReference.addSnapshotListener(listener: (snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration

expect suspend fun DocumentReference.awaitGet(): DocumentSnapshot

expect suspend fun DocumentReference.awaitSet(data: Map<String, Any>)

expect suspend fun DocumentReference.awaitSet(pojo: Any)

expect suspend fun DocumentReference.awaitSet(data: Map<String, Any>, options: SetOptions)

expect suspend fun DocumentReference.awaitSet(pojo: Any, options: SetOptions)

expect suspend fun DocumentReference.awaitUpdate(data: Map<String, Any>)

expect suspend fun DocumentReference.awaitDelete()

expect class CollectionReference : Query

expect suspend fun CollectionReference.awaitAdd(data: Map<String, Any>): DocumentReference

expect suspend fun CollectionReference.awaitAdd(pojo: Any): DocumentReference

expect class FirebaseFirestoreException : FirebaseException

expect val FirebaseFirestoreException.code: FirestoreExceptionCode

expect enum class FirestoreExceptionCode {
    OK,
    CANCELLED,
    UNKNOWN,
    INVALID_ARGUMENT,
    DEADLINE_EXCEEDED,
    NOT_FOUND,
    ALREADY_EXISTS,
    PERMISSION_DENIED,
    RESOURCE_EXHAUSTED,
    FAILED_PRECONDITION,
    ABORTED,
    OUT_OF_RANGE,
    UNIMPLEMENTED,
    INTERNAL,
    UNAVAILABLE,
    DATA_LOSS,
    UNAUTHENTICATED
}

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