package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.Deferred
import kotlin.reflect.KClass
import multiplatform.com.google.firebase.FirebaseException

actual fun getFirebaseFirestore(): FirebaseFirestore {
    TODO("not implemented")
}

actual class FirebaseFirestore {
    actual fun getFirestoreSettings(): FirebaseFirestoreSettings {
        TODO("not implemented")
    }

    actual fun setFirestoreSettings(settings: FirebaseFirestoreSettings) {}

    actual fun collection(collectionPath: String): CollectionReference {
        TODO("not implemented")
    }
    actual fun document(documentPath: String): DocumentReference {
        TODO("not implemented")
    }

    actual fun batch(): WriteBatch {
        TODO("not implemented")
    }

}

actual class FirebaseFirestoreSettingsBuilder actual constructor() {
    actual constructor(settings: FirebaseFirestoreSettings) : this() {

    }

    actual fun setPersistenceEnabled(enabled: Boolean): FirebaseFirestoreSettingsBuilder {
        TODO("not implemented")
    }

    actual fun setTimestampsInSnapshotsEnabled(enabled: Boolean): FirebaseFirestoreSettingsBuilder {
        TODO("not implemented")
    }

    actual fun build(): FirebaseFirestoreSettings {
        TODO("not implemented")
    }
}

actual class FirebaseFirestoreSettings

actual open class Query {
    actual fun whereEqualTo(field: String, value: Any?): Query {
        TODO("not implemented")
    }

    actual fun whereEqualTo(path: FieldPath, value: Any?): Query {
        TODO("not implemented")
    }

    actual fun whereGreaterThan(field: String, value: Any): Query {
        TODO("not implemented")
    }

    actual fun whereGreaterThan(path: FieldPath, value: Any): Query {
        TODO("not implemented")
    }

    actual fun addSnapshotListener(listener: EventListener<QuerySnapshot>): ListenerRegistration {
        TODO("not implemented")
    }

    actual fun whereArrayContains(field: String, value: Any): Query {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun whereArrayContains(path: FieldPath, value: Any): Query {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

actual fun Query.getAsync(): Deferred<QuerySnapshot> {
    TODO("not implemented")
}

actual class CollectionReference : Query()
actual class FirebaseFirestoreException : FirebaseException()
actual class QuerySnapshot

actual val QuerySnapshot.documents: List<DocumentSnapshot>
    get() = TODO("not implemented")

actual class DocumentSnapshot {
    actual fun get(field: String): Any? {
        TODO("not implemented")
    }

    actual fun getString(field: String): String? {
        TODO("not implemented")
    }

    actual fun contains(field: String): Boolean {
        TODO("not implemented")
    }
}

actual fun <T : Any> DocumentSnapshot.toObject(valueType: KClass<T>): T {
    TODO("not implemented")
}

actual val DocumentSnapshot.id: String
    get() = TODO("not implemented")

actual interface ListenerRegistration {
    actual fun remove()
}

actual interface EventListener<T> {
    actual fun onEvent(snapshot: T?, exception: FirebaseFirestoreException?)
}

actual fun fieldPathOf(vararg fieldNames: String): FieldPath {
    TODO("not implemented")
}

actual class FieldPath

actual fun Query.addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration {
    TODO("not implemented")
}

actual class DocumentReference {
    actual fun addSnapshotListener(listener: EventListener<DocumentSnapshot>): ListenerRegistration {
        TODO("not implemented")
    }
}


actual class SetOptions

actual fun mergeSetOptions(): SetOptions {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
actual val DocumentReference.id: String
    get() = TODO("not implemented")

actual fun DocumentReference.setAsync(data: Map<String, Any>): Deferred<*> {
    TODO("not implemented")
}

actual fun DocumentReference.setAsync(pojo: Any): Deferred<*> {
    TODO("not implemented")
}

actual fun DocumentReference.setAsync(data: Map<String, Any>, options: SetOptions): Deferred<*> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentReference.setAsync(pojo: Any, options: SetOptions): Deferred<*> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentReference.updateAsync(data: Map<String, Any>): Deferred<*> {
    TODO("not implemented")
}

actual fun CollectionReference.addAsync(data: Map<String, Any>): Deferred<DocumentReference> {
    TODO("not implemented")
}

actual fun CollectionReference.addAsync(pojo: Any): Deferred<DocumentReference> {
    TODO("not implemented")
}

actual fun DocumentReference.addSnapshotListener(listener: (snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration {
    TODO("not implemented")
}

actual fun DocumentReference.deleteAsync(): Deferred<*> {
    TODO("not implemented")
}

actual class WriteBatch {
    actual fun set(documentRef: DocumentReference, data: Map<String, Any>): WriteBatch {
        TODO("not implemented")
    }

    actual fun set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions): WriteBatch {
        TODO("not implemented")
    }

    actual fun set(documentRef: DocumentReference, pojo: Any): WriteBatch {
        TODO("not implemented")
    }

    actual fun set(documentRef: DocumentReference, pojo: Any, options: SetOptions): WriteBatch {
        TODO("not implemented")
    }

    actual fun update(documentRef: DocumentReference, data: Map<String, Any>): WriteBatch {
        TODO("not implemented")
    }

    actual fun update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any): WriteBatch {
        TODO("not implemented")
    }

    actual fun update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any): WriteBatch {
        TODO("not implemented")
    }

    actual fun delete(documentRef: DocumentReference): WriteBatch {
        TODO("not implemented")
    }

}

actual fun WriteBatch.commitAsync(): Deferred<*> {
    TODO("not implemented")
}

actual fun DocumentReference.getAsync(): Deferred<DocumentSnapshot> {
    TODO("not implemented")
}

actual class Transaction {
    actual fun set(documentRef: DocumentReference, data: Map<String, Any>): Transaction {
        TODO("not implemented")
    }

    actual fun set(documentRef: DocumentReference, data: Map<String, Any>, options: SetOptions): Transaction {
        TODO("not implemented")
    }

    actual fun set(documentRef: DocumentReference, pojo: Any): Transaction {
        TODO("not implemented")
    }

    actual fun set(documentRef: DocumentReference, pojo: Any, options: SetOptions): Transaction {
        TODO("not implemented")
    }

    actual fun update(documentRef: DocumentReference, data: Map<String, Any>): Transaction {
        TODO("not implemented")
    }

    actual fun update(documentRef: DocumentReference, field: String, value: Any?, vararg moreFieldsAndValues: Any): Transaction {
        TODO("not implemented")
    }

    actual fun update(documentRef: DocumentReference, fieldPath: FieldPath, value: Any?, vararg moreFieldsAndValues: Any): Transaction {
        TODO("not implemented")
    }

    actual fun delete(documentRef: DocumentReference): Transaction {
        TODO("not implemented")
    }

    actual fun get(documentRef: DocumentReference): DocumentSnapshot {
        TODO("not implemented")
    }
}

actual fun <T> FirebaseFirestore.runTransactionAsync(func: (transaction: Transaction) -> T): Deferred<T> {
    TODO("not implemented")
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