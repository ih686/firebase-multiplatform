package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.Deferred
import multiplatform.com.google.firebase.FirebaseException
import kotlin.js.json
import kotlin.reflect.KClass

@JsModule("firebase")
external val firebase: dynamic
@JsModule("firebase/firestore")
external val firestore: dynamic

actual class FirebaseFirestore {

    private val db = firebase.firestore()

    init {
        db.settings(json(
            "timestampsInSnapshots" to true
        ))
    }

    actual companion object {
        private val instance = FirebaseFirestore()
        actual fun getInstance() = instance
    }

    actual fun collection(collectionPath: String) = CollectionReference(/*db.collection(collectionPath)*/)

    actual fun setFirestoreSettings(settings: FirebaseFirestoreSettings) {}

    actual fun getFirestoreSettings(): FirebaseFirestoreSettings {
        TODO("not implemented")
    }
}

actual external class QuerySnapshot {
    actual val documents: List<DocumentSnapshot>
        get() = definedExternally
}

actual open external class Query {
    actual fun whereGreaterThan(field: String, value: Any): Query

    actual fun get(): Deferred<QuerySnapshot>

    actual fun addSnapshotListener(listener: EventListener<QuerySnapshot>): ListenerRegistration
    actual fun addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit): ListenerRegistration
    actual fun whereGreaterThan(path: FieldPath, value: Any): Query
}

actual class CollectionReference : Query() {
}

actual class FirebaseFirestoreException : FirebaseException()

actual interface ListenerRegistration {
    actual fun remove()
}

actual interface EventListener<T> {
    actual fun onEvent(snapshot: T?, exception: FirebaseFirestoreException?)
}

actual class FieldPath {
    actual companion object {
        actual fun of(vararg fieldNames: String): FieldPath {
            TODO("not implemented")
        }
    }
}

actual class DocumentSnapshot {
    actual fun <T : Any> toObject(valueType: KClass<T>): T {
        TODO("not implemented")
    }
}

actual class FirebaseFirestoreSettings {
    actual class Builder actual constructor() {
        actual fun setPersistenceEnabled(enabled: Boolean): Builder {
            TODO("not implemented")
        }

        actual fun setTimestampsInSnapshotsEnabled(enabled: Boolean): Builder {
            TODO("not implemented")
        }

        actual fun build(): FirebaseFirestoreSettings {
            TODO("not implemented")
        }

        actual constructor(settings: FirebaseFirestoreSettings) : this()
    }
}