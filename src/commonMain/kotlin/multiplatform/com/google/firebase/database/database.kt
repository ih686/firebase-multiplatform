package multiplatform.com.google.firebase.database

import kotlinx.coroutines.Deferred
import kotlin.reflect.KClass

expect fun getFirebaseDatabase(): FirebaseDatabase


expect class FirebaseDatabase {
    fun getReference(path: String): DatabaseReference
    fun setPersistenceEnabled(enabled: Boolean)
}

expect class DatabaseReference {
    fun push(): DatabaseReference
    fun onDisconnect(): OnDisconnect
    fun addValueEventListener(listener: ValueEventListener): ValueEventListener
    fun removeEventListener(listener: ValueEventListener)
}

expect suspend fun DatabaseReference.awaitSetValue(value: Any?)

expect interface ValueEventListener {
    fun onDataChange(data: DataSnapshot)
    fun onCancelled(error: DatabaseError)
}

expect class DataSnapshot

expect fun <T: Any> DataSnapshot.getValue(valueType: KClass<T>): T

expect val TIMESTAMP: Map<String, String>


expect class DatabaseError {

}

expect class OnDisconnect

expect suspend fun OnDisconnect.awaitRemoveValue()
expect suspend fun OnDisconnect.awaitCancel()
expect suspend fun OnDisconnect.awaitSetValue(value: Any?)
