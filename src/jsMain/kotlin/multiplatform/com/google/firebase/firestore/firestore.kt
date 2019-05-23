package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.promise
import multiplatform.com.google.firebase.*
import multiplatform.com.google.firebase.fromJson
import multiplatform.com.google.firebase.toJson
import kotlin.reflect.KClass
import kotlin.js.Json
import kotlin.js.json


actual fun getFirebaseFirestore() = firebase.firestore()

actual typealias FirebaseFirestore = firebase.firestore.Firestore
actual typealias QuerySnapshot = firebase.firestore.QuerySnapshot
actual typealias DocumentSnapshot = firebase.firestore.DocumentSnapshot
actual typealias Query = firebase.firestore.Query
actual typealias DocumentReference = firebase.firestore.DocumentReference
actual typealias WriteBatch = firebase.firestore.WriteBatch
actual typealias Transaction = firebase.firestore.Transaction
actual typealias SetOptions = NewOptions
actual typealias CollectionReference = firebase.firestore.CollectionReference
actual typealias FieldPath = firebase.firestore.FieldPath


actual data class FirebaseFirestoreSettings internal constructor(
    val cacheSizeBytes: Number? = undefined,
    val host: String? = undefined,
    val ssl: Boolean? = undefined,
    var timestampsInSnapshots: Boolean? = undefined,
    var enablePersistence: Boolean = false
)

actual class FirebaseFirestoreSettingsBuilder actual constructor(internal var settings: FirebaseFirestoreSettings) {
    actual constructor() : this(FirebaseFirestoreSettings())
}

actual fun FirebaseFirestoreSettingsBuilder.setPersistenceEnabled(enabled: Boolean) = settings.copy( enablePersistence = enabled ).let { settings = it }.let{ this }

actual fun FirebaseFirestoreSettingsBuilder.setTimestampsInSnapshotsEnabled(enabled: Boolean) = settings.copy( timestampsInSnapshots = enabled ).let { settings = it }.let { this }

actual fun FirebaseFirestoreSettingsBuilder.build() = settings


actual fun FirebaseFirestore.getFirestoreSettings() = _th_settings ?: FirebaseFirestoreSettings()

actual fun FirebaseFirestore.setFirestoreSettings(settings: FirebaseFirestoreSettings) {
    _th_settings = settings
    settings(json(
            "cacheSizeBytes" to settings.cacheSizeBytes,
            "host" to settings.host,
            "ssl" to settings.ssl,
            "timestampsInSnapshots" to settings.timestampsInSnapshots
    ))
    if(settings.enablePersistence) enablePersistence()
}

actual class FirebaseFirestoreException
    actual constructor(detailMessage: String, code: FirestoreExceptionCode) : FirebaseException(FirebaseError()
        .apply{
            message = detailMessage
            this.code = code.name
        })

actual val QuerySnapshot.documents: List<DocumentSnapshot>
    get() = docs.toList()

@Suppress("UNCHECKED_CAST")
actual fun <T : Any> DocumentSnapshot.toObject(valueType: KClass<T>): T = fromJson(data(), valueType)  as T

actual val DocumentSnapshot.id: String
    get() = id

actual interface ListenerRegistration


actual interface EventListener<T> {
    actual fun onEvent(snapshot: T?, exception: FirebaseFirestoreException?)
}

actual fun fieldPathOf(vararg fieldNames: String) = FieldPath(fieldNames)

actual fun Query.addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit) = onSnapshot({ listener(it, undefined) }, { listener(undefined, FirebaseFirestoreException(it.message as String, FirestoreExceptionCode.UNKNOWN)) })
        .also { it.asDynamic().remove = { it() } }
        .asDynamic()


actual fun Query.whereEqualTo(field: String, value: Any?) = where(field, "==", value)

actual fun Query.whereEqualTo(path: FieldPath, value: Any?) = where(path, "==", value)

actual fun Query.whereLessThan(field: String, value: Any) = where(field, "<", value)

actual fun Query.whereLessThan(path: FieldPath, value: Any) = where(path, "<", value)

actual fun Query.whereGreaterThan(field: String, value: Any) = where(field, ">", value)

actual fun Query.whereGreaterThan(path: FieldPath, value: Any) = where(path, ">", value)

actual fun Query.whereArrayContains(field: String, value: Any) = where(field, "array-contains", value)

actual fun Query.whereArrayContains(path: FieldPath, value: Any) = where(path, "array-contains", value)

actual fun Query.addSnapshotListener(listener: EventListener<QuerySnapshot>) = onSnapshot(
        { listener.onEvent(snapshot = it, exception = undefined)},
        { listener.onEvent(snapshot = undefined, exception = FirebaseFirestoreException(it.message as String, FirestoreExceptionCode.UNKNOWN))})
        .also { it.asDynamic().remove = { it() } }
        .asDynamic()


class NewOptions {
    val merge: Boolean = true
}

actual fun mergeSetOptions() = NewOptions()

actual val DocumentReference.id: String
    get() = id

actual fun DocumentReference.addSnapshotListener(listener: (snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) -> Unit)  = onSnapshot({ listener(it, undefined) }, { listener(undefined, FirebaseFirestoreException(it.message as String, FirestoreExceptionCode.UNKNOWN)) })
        .also { it.asDynamic().remove = { it() } }
        .asDynamic()

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

actual suspend fun <T> FirebaseFirestore.awaitRunTransaction(func: suspend (transaction: Transaction) -> T) =
    runTransaction { GlobalScope.promise { func(it) } }.await()


actual suspend fun WriteBatch.awaitCommit() = commit().await()

actual suspend fun Query.awaitGet() = get().await()

actual suspend fun DocumentReference.awaitGet() = get().await()

actual suspend fun DocumentReference.awaitSet(data: Map<String, Any>) = set(toJson(data)!!).await()

actual suspend fun DocumentReference.awaitSet(pojo: Any) = set(toJson(pojo)!!).await()

actual suspend fun DocumentReference.awaitSet(data: Map<String, Any>, options: SetOptions) = set(toJson(data)!!, options).await()

actual suspend fun DocumentReference.awaitSet(pojo: Any, options: SetOptions) = set(toJson(pojo)!!, options).await()

actual suspend fun DocumentReference.awaitUpdate(data: Map<String, Any>) = update(toJson(data)!!).await()

actual suspend fun DocumentReference.awaitDelete() = delete().await()

actual suspend fun CollectionReference.awaitAdd(data: Map<String, Any>) = add(toJson(data)!!).await()

actual suspend fun CollectionReference.awaitAdd(pojo: Any) = add(toJson(pojo)!!).await()

actual fun FirebaseFirestore.collection(collectionPath: String) = collection(collectionPath)

actual fun FirebaseFirestore.document(documentPath: String) = doc(documentPath)

actual fun FirebaseFirestore.batch() = batch()

actual fun FirebaseFirestore.setLoggingEnabled(loggingEnabled: Boolean) = firebase.firestore.setLogLevel( if(loggingEnabled) "error" else "silent")

actual fun Transaction.set(documentRef: DocumentReference, data: Map<String, Any>) = set(documentRef, toJson(data)!!).let { this }

actual fun Transaction.set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions) = set(documentRef, toJson(data)!!, options).let { this }

actual fun Transaction.set(documentRef: DocumentReference, pojo: Any) = set(documentRef, toJson(pojo)!!).let { this }

actual fun Transaction.set(documentRef: DocumentReference, pojo: Any, options: SetOptions) = set(documentRef, toJson(pojo)!!, options).let { this }

actual fun Transaction.update(documentRef: DocumentReference, data: Map<String, Any>) = update(documentRef, toJson(data)!!).let { this }

actual fun Transaction.update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any) = update(documentRef, field, value, moreFieldsAndValues)

actual fun Transaction.update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any) = update(documentRef, fieldPath, value, moreFieldsAndValues)

actual fun Transaction.delete(documentRef: DocumentReference) = delete(documentRef).let { this }

actual suspend fun Transaction.awaitGet(documentRef: DocumentReference) = get(documentRef).await()


actual fun WriteBatch.set(documentRef: DocumentReference, data: Map<String, Any>) = set(documentRef, toJson(data)!!)

actual fun WriteBatch.set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions) = set(documentRef, toJson(data)!!, options)

actual fun WriteBatch.set(documentRef: DocumentReference, pojo: Any) = set(documentRef, toJson(pojo)!!)

actual fun WriteBatch.set(documentRef: DocumentReference, pojo: Any, options: SetOptions) = set(documentRef, toJson(pojo)!!, options)

actual fun WriteBatch.update(documentRef: DocumentReference, data: Map<String, Any>) = update(documentRef, data)

actual fun WriteBatch.update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any) = update(documentRef, field, value, arrayOf(moreFieldsAndValues))

actual fun WriteBatch.update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any) = update(documentRef, fieldPath, value, arrayOf(moreFieldsAndValues))

actual fun WriteBatch.delete(documentRef: DocumentReference) = delete(documentRef)

actual fun DocumentReference.addSnapshotListener(listener: EventListener<DocumentSnapshot>) = onSnapshot(
        { listener.onEvent(snapshot = it, exception = undefined)},
        { listener.onEvent(snapshot = undefined, exception = FirebaseFirestoreException(it.message as String, FirestoreExceptionCode.UNKNOWN))})
        .also { it.asDynamic().remove = { it() } }
        .asDynamic()

actual fun DocumentSnapshot.get(field: String) = get(field)

actual fun DocumentSnapshot.getString(field: String) = get(field) as String?

actual fun DocumentSnapshot.contains(field: String) = get(field) != undefined

actual fun ListenerRegistration.remove() {
    TODO("Not implemented")
}


actual annotation class IgnoreExtraProperties

actual annotation class Exclude

actual fun deleteFieldValue(): FieldValue {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual abstract class FieldValue

actual fun DocumentSnapshot.exists() = exists

actual fun arrayUnionFieldValue(vararg elements: Any): FieldValue {
    TODO("not implemented")
}

actual fun arrayRemoveFieldValue(vararg elements: Any): FieldValue {
    TODO("not implemented")
}


actual suspend fun DocumentReference.awaitUpdate(field: String, value: Any?, vararg moreFieldsAndValues: Any) {
    TODO("not implemented")
}

actual suspend fun DocumentReference.awaitUpdate(fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any) {
    TODO("not implemented")
}