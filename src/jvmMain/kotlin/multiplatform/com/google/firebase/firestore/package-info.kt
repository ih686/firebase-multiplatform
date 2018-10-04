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

actual class CollectionReference {
    actual fun get(): Deferred<QuerySnapshot> {
        TODO("not implemented") //To change body of created multiplatform.com.google.firebase.functions use File | Settings | File Templates.
    }
}

actual class QuerySnapshot