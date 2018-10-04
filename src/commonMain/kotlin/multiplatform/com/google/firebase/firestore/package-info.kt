package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.Deferred

expect class FirebaseFirestore {
    companion object {
        fun getInstance(): FirebaseFirestore
    }

    fun collection(collectionPath: String): CollectionReference
}

expect class CollectionReference {
    fun get(): Deferred<QuerySnapshot>
}

expect class QuerySnapshot