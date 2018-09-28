package multiplatform.com.google.firebase.firestore

actual class FirebaseFirestore {
    actual companion object {
        actual fun getInstance(): FirebaseFirestore {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    actual fun collection(collectionPath: String): CollectionReference {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

actual class CollectionReference {
    actual suspend fun get(): QuerySnapshot {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

actual class QuerySnapshot