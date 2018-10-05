package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.Deferred

actual class FirebaseFirestore {
    actual companion object {
        actual fun getInstance(): FirebaseFirestore {
            TODO("not implemented") //To change body of created multiplatform.com.google.firebase.functions use File | Settings | File Templates.
        }
    }

    actual fun collection(collectionPath: String): CollectionReference {
        TODO("not implemented") //To change body of created multiplatform.com.google.firebase.functions use File | Settings | File Templates.
    }

}

actual class QuerySnapshot

actual open class Query {
    actual fun whereGreaterThan(field: String, value: Any): Query {
        TODO("not implemented")
    }

    actual fun get(): Deferred<QuerySnapshot> {
        TODO("not implemented")
    }

    actual fun addSnapshotListener(listener: EventListener<QuerySnapshot>): ListenerRegistration {
        TODO("not implemented")
    }
}

actual class CollectionReference : Query() {
}

actual class FirebaseFirestoreException