package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.await
import multiplatform.com.google.firebase.FirebaseException
import kotlin.js.Promise
import kotlin.reflect.KClass

@JsModule("firebase")
external val firebase: dynamic

@JsModule("firebase/firestore")
external val firestore: dynamic

actual fun getFirebaseFirestore() = firebase.firestore()

@JsModule("firebase/firestore")
open external class Firestore {
    fun <T> runTransaction(func: (transaction: Transaction) -> Promise<T>): Promise<T>
}

actual typealias FirebaseFirestore = Firestore

actual data class FirebaseFirestoreSettings internal constructor(
        private val cacheSizeBytes: Number = 40,
        private val host: String = "",
        private val ssl: Boolean = false,
        private val timestampInSnapshots: Boolean = true
)

actual class FirebaseFirestoreSettingsBuilder actual constructor() {
    internal var settings = FirebaseFirestoreSettings()

    actual constructor(settings: FirebaseFirestoreSettings) : this() {
        this.settings = settings
    }
}

// TODO: see TODO above ...
actual fun FirebaseFirestoreSettingsBuilder.setPersistenceEnabled(enabled: Boolean): FirebaseFirestoreSettingsBuilder {
    TODO("not implemented")
}

actual fun FirebaseFirestoreSettingsBuilder.setTimestampsInSnapshotsEnabled(enabled: Boolean) = settings.copy(timestampInSnapshots = enabled).let { this }

actual fun FirebaseFirestoreSettingsBuilder.build() = settings

@JsModule("firebase/firestore")
actual open external class Query {
    fun get(options: Any? = definedExternally): Promise<QuerySnapshot>
    fun where(fieldPath: Any, opStr: String, value: Any?): Query
}

@JsModule("firebase/firestore")
actual open external class CollectionReference : Query {
    fun add(data: Any): Promise<DocumentReference>
}

actual class FirebaseFirestoreException
    actual constructor(detailMessage: String, code: FirestoreExceptionCode) : FirebaseException()

@JsModule("firebase/firestore")
actual open external class QuerySnapshot {
    val docs: List<DocumentSnapshot>
}


actual val QuerySnapshot.documents: List<DocumentSnapshot>
    get() = docs

@JsModule("firebase/firestore")
actual open external class DocumentSnapshot {
    val id: String

    fun get(fieldPath: Any, options: Any? = definedExternally): Any?
}

actual fun <T : Any> DocumentSnapshot.toObject(valueType: KClass<T>) = valueType as T

actual val DocumentSnapshot.id: String
    get() = id

actual interface ListenerRegistration

actual interface EventListener<T> {
    actual fun onEvent(snapshot: T?, exception: FirebaseFirestoreException?)
}

actual fun fieldPathOf(vararg fieldNames: String) = FieldPath(fieldNames)

@JsModule("firebase/firestore")
actual open external class FieldPath internal constructor(fieldNames: Array<out String>)

actual fun Query.addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration {
    TODO("not implemented")
}

actual fun Query.whereEqualTo(field: String, value: Any?) = where(field, "=", value).let { this }

actual fun Query.whereEqualTo(path: FieldPath, value: Any?) = where(path, "=", value).let { this }

actual fun Query.whereGreaterThan(field: String, value: Any) = where(field, ">", value).let { this }

actual fun Query.whereGreaterThan(path: FieldPath, value: Any) = where(path, ">", value).let { this }

actual fun Query.whereArrayContains(field: String, value: Any) = where(field, "array-contains", value).let { this }

actual fun Query.whereArrayContains(path: FieldPath, value: Any) = where(path, "array-contains", value).let { this }

actual fun Query.addSnapshotListener(listener: EventListener<QuerySnapshot>): ListenerRegistration {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}


@JsModule("firebase/firestore")
actual open external class DocumentReference {
    val id: String

    fun get(options: Any? = definedExternally): Promise<DocumentSnapshot>
    fun set(data: Any, options: Any? = definedExternally): Promise<Unit>
    fun update(data: Any): Promise<Unit>
    fun delete(): Promise<Unit>
    fun onSnapshot(observer: Any): ListenerRegistration
}

@JsModule("firebase/firestore")
actual open external class SetOptions {
    companion object {
        var merge: Boolean
    }
}

actual fun mergeSetOptions(): SetOptions {
    TODO("Not implemented")
}

actual val DocumentReference.id: String
    get() = id

actual fun DocumentReference.addSnapshotListener(listener: (snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration {
    TODO("not implemented")
}

@JsModule("firebase/firestore")
actual open external class WriteBatch {
    fun commit(): Promise<Unit>
    fun delete(documentReference: DocumentReference): WriteBatch
    fun set(documentReference: DocumentReference, data: Any, options: Any? = definedExternally): WriteBatch
    fun update(documentReference: DocumentReference, data: Any): WriteBatch
    fun update(documentReference: DocumentReference, field: Any, value: Any?, vararg moreFieldsAndValues: Array<out Any>): WriteBatch
}

@JsModule("firebase/firestore")
actual open external class Transaction {
    fun get(documentRefence: DocumentReference): Promise<DocumentSnapshot>
    fun set(documentReference: DocumentReference, data: Any, options: Any? = definedExternally): Transaction
    fun update(documentReference: DocumentReference, data: Any): Transaction
    fun update(documentReference: DocumentReference, field: Any, value: Any?, vararg moreFieldsAndValues: Array<out Any>): Transaction
    fun delete(documentReference: DocumentReference): Transaction
}

actual val FirebaseFirestoreException.code: FirestoreExceptionCode
    get() = TODO("not implemented")

actual enum class FirestoreExceptionCode {
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

actual suspend fun <T> FirebaseFirestore.awaitRunTransaction(func: (transaction: Transaction) -> T) = runTransaction(func).await()

actual suspend fun WriteBatch.awaitCommit() = commit().await()

actual suspend fun Query.awaitGet() = get().await()

actual suspend fun DocumentReference.awaitGet() = get().await()

actual suspend fun DocumentReference.awaitSet(data: Map<String, Any>) = set(data).await()

actual suspend fun DocumentReference.awaitSet(pojo: Any) = set(pojo).await()

actual suspend fun DocumentReference.awaitSet(data: Map<String, Any>, options: SetOptions) = set(data, options).await()

actual suspend fun DocumentReference.awaitSet(pojo: Any, options: SetOptions) = set(pojo, options).await()

actual suspend fun DocumentReference.awaitUpdate(data: Map<String, Any>) = update(data).await()

actual suspend fun DocumentReference.awaitDelete() = delete().await()

actual suspend fun CollectionReference.awaitAdd(data: Map<String, Any>) = add(data).await()

actual suspend fun CollectionReference.awaitAdd(pojo: Any) = add(pojo).await()

actual fun FirebaseFirestore.getFirestoreSettings(): FirebaseFirestoreSettings {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseFirestore.setFirestoreSettings(settings: FirebaseFirestoreSettings) {
}

actual fun FirebaseFirestore.collection(collectionPath: String) = getFirebaseFirestore().collection(collectionPath)

actual fun FirebaseFirestore.document(documentPath: String) = getFirebaseFirestore().doc(documentPath)

actual fun FirebaseFirestore.batch() = getFirebaseFirestore().batch()

actual fun Transaction.set(documentRef: DocumentReference, data: Map<String, Any>) = set(documentRef, data).let { this }

actual fun Transaction.set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions) = set(documentRef, data, options).let { this }

actual fun Transaction.set(documentRef: DocumentReference, pojo: Any) = set(documentRef, pojo).let { this }

actual fun Transaction.set(documentRef: DocumentReference, pojo: Any, options: SetOptions) = set(documentRef, pojo, options).let { this }

actual fun Transaction.update(documentRef: DocumentReference, data: Map<String, Any>) = update(documentRef, data).let { this }

actual fun Transaction.update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any) = update(documentRef, field, value, moreFieldsAndValues)

actual fun Transaction.update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any) = update(documentRef, fieldPath, value, moreFieldsAndValues)

actual fun Transaction.delete(documentRef: DocumentReference) = delete(documentRef).let { this }

actual suspend fun Transaction.get(documentRef: DocumentReference) = get(documentRef).await()


actual fun WriteBatch.set(documentRef: DocumentReference, data: Map<String, Any>) = set(documentRef, data)

actual fun WriteBatch.set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions) = set(documentRef, data, options)

actual fun WriteBatch.set(documentRef: DocumentReference, pojo: Any) = set(documentRef, pojo)

actual fun WriteBatch.set(documentRef: DocumentReference, pojo: Any, options: SetOptions) = set(documentRef, pojo, options)

actual fun WriteBatch.update(documentRef: DocumentReference, data: Map<String, Any>) = update(documentRef, data)

actual fun WriteBatch.update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any) = update(documentRef, field, value, arrayOf(moreFieldsAndValues))

actual fun WriteBatch.update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any) = update(documentRef, fieldPath, value, arrayOf(moreFieldsAndValues))

actual fun WriteBatch.delete(documentRef: DocumentReference) = delete(documentRef)

actual fun DocumentReference.addSnapshotListener(listener: EventListener<DocumentSnapshot>) = onSnapshot(listener)

actual fun DocumentSnapshot.get(field: String) = get(field)

actual fun DocumentSnapshot.getString(field: String) = get(field) as String?

actual fun DocumentSnapshot.contains(field: String) = get(field) != undefined

actual fun ListenerRegistration.remove() {
    TODO("Not implemented")
}

