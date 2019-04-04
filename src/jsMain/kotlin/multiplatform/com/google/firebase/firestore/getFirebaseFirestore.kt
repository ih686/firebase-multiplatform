package multiplatform.com.google.firebase.firestore

import multiplatform.com.google.firebase.FirebaseException
import kotlin.reflect.KClass

actual fun getFirebaseFirestore(): FirebaseFirestore {
    TODO("not implemented")
}

actual class FirebaseFirestore

actual class FirebaseFirestoreSettingsBuilder actual constructor() {
    actual constructor(settings: FirebaseFirestoreSettings) : this() {

    }
}

actual class FirebaseFirestoreSettings

actual open class Query

actual class CollectionReference : Query()
actual class FirebaseFirestoreException actual constructor(detailMessage: String, code: FirestoreExceptionCode) : FirebaseException()
actual class QuerySnapshot

actual val QuerySnapshot.documents: List<DocumentSnapshot>
    get() = TODO("not implemented")

actual class DocumentSnapshot

actual fun <T : Any> DocumentSnapshot.toObject(valueType: KClass<T>): T {
    TODO("not implemented")
}

actual val DocumentSnapshot.id: String
    get() = TODO("not implemented")

actual interface ListenerRegistration

actual interface EventListener<T>

actual fun fieldPathOf(vararg fieldNames: String): FieldPath {
    TODO("not implemented")
}

actual class FieldPath

actual fun Query.addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration {
    TODO("not implemented")
}

actual class DocumentReference


actual class SetOptions

actual fun mergeSetOptions(): SetOptions {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
actual val DocumentReference.id: String
    get() = TODO("not implemented")

actual fun DocumentReference.addSnapshotListener(listener: (snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration {
    TODO("not implemented")
}

actual class WriteBatch

actual class Transaction

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

actual suspend fun <T> FirebaseFirestore.awaitRunTransaction(func: (transaction: Transaction) -> T): T {
    TODO("not implemented")
}

actual suspend fun WriteBatch.awaitCommit() {}

actual suspend fun Query.awaitGet(): QuerySnapshot {
    TODO("not implemented")
}

actual suspend fun DocumentReference.awaitGet(): DocumentSnapshot {
    TODO("not implemented")
}

actual suspend fun DocumentReference.awaitSet(data: Map<String, Any>) {}

actual suspend fun DocumentReference.awaitSet(pojo: Any) {}

actual suspend fun DocumentReference.awaitSet(data: Map<String, Any>, options: SetOptions) {}

actual suspend fun DocumentReference.awaitSet(pojo: Any, options: SetOptions) {}

actual suspend fun DocumentReference.awaitUpdate(data: Map<String, Any>) {}

actual suspend fun DocumentReference.awaitDelete() {}

actual suspend fun CollectionReference.awaitAdd(data: Map<String, Any>): DocumentReference {
    TODO("not implemented")
}

actual suspend fun CollectionReference.awaitAdd(pojo: Any): DocumentReference {
    TODO("not implemented")
}

actual fun FirebaseFirestore.getFirestoreSettings(): FirebaseFirestoreSettings {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseFirestore.setFirestoreSettings(settings: FirebaseFirestoreSettings) {
}

actual fun FirebaseFirestore.collection(collectionPath: String): CollectionReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseFirestore.document(documentPath: String): DocumentReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseFirestore.batch(): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.set(documentRef: DocumentReference, data: Map<String, Any>): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.set(documentRef: DocumentReference, pojo: Any): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.set(documentRef: DocumentReference, pojo: Any, options: SetOptions): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.update(documentRef: DocumentReference, data: Map<String, Any>): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.delete(documentRef: DocumentReference): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.get(documentRef: DocumentReference): DocumentSnapshot {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseFirestoreSettingsBuilder.setPersistenceEnabled(enabled: Boolean): FirebaseFirestoreSettingsBuilder {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseFirestoreSettingsBuilder.setTimestampsInSnapshotsEnabled(enabled: Boolean): FirebaseFirestoreSettingsBuilder {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseFirestoreSettingsBuilder.build(): FirebaseFirestoreSettings {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Query.whereEqualTo(field: String, value: Any?): Query {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Query.whereEqualTo(path: FieldPath, value: Any?): Query {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Query.whereGreaterThan(field: String, value: Any): Query {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Query.whereGreaterThan(path: FieldPath, value: Any): Query {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Query.whereArrayContains(field: String, value: Any): Query {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Query.whereArrayContains(path: FieldPath, value: Any): Query {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Query.addSnapshotListener(listener: EventListener<QuerySnapshot>): ListenerRegistration {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun WriteBatch.set(documentRef: DocumentReference, data: Map<String, Any>): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun WriteBatch.set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun WriteBatch.set(documentRef: DocumentReference, pojo: Any): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun WriteBatch.set(documentRef: DocumentReference, pojo: Any, options: SetOptions): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun WriteBatch.update(documentRef: DocumentReference, data: Map<String, Any>): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun WriteBatch.update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun WriteBatch.update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun WriteBatch.delete(documentRef: DocumentReference): WriteBatch {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentReference.addSnapshotListener(listener: EventListener<DocumentSnapshot>): ListenerRegistration {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentSnapshot.get(field: String): Any? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentSnapshot.getString(field: String): String? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentSnapshot.contains(field: String): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun ListenerRegistration.remove() {
}

actual fun <T> EventListener<T>.onEvent(snapshot: T?, exception: FirebaseFirestoreException?) {
}