package multiplatform.com.google.firebase.firestore

import kotlin.js.json

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

    actual fun collection(collectionPath: String) = CollectionReference(db.collection(collectionPath))
}

actual class CollectionReference(private val ref: dynamic) {
    actual fun get() = ref.get().asDeferred()
}

actual external class QuerySnapshot