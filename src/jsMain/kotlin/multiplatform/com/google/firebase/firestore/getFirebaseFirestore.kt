package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import multiplatform.com.google.firebase.FirebaseException
import kotlin.reflect.KClass

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

actual fun DocumentReference.setAsync(data: Map<String, Any>): Job {
    TODO("not implemented")
}

actual fun DocumentReference.setAsync(pojo: Any): Job {
    TODO("not implemented")
}

actual class SetOptions

actual fun mergeSetOptions(): SetOptions {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentReference.setAsync(data: Map<String, Any>, options: SetOptions): Job {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DocumentReference.setAsync(pojo: Any, options: SetOptions): Job {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

actual fun DocumentReference.delete(): Job {
    TODO("not implemented")
}