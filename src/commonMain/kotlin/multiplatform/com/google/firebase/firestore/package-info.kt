package multiplatform.com.google.firebase.firestore

expect class FirebaseFirestore {
    companion object {
        fun getInstance(): FirebaseFirestore
    }

    fun collection(collectionPath: String): CollectionReference
}

expect class CollectionReference {
    suspend fun get(): QuerySnapshot
}

expect class QuerySnapshot