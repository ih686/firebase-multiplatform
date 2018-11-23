package multiplatform.com.google.firebase.database

import kotlinx.coroutines.Job
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

expect fun DatabaseReference.setValueAsync(value: Any?): Job


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

expect fun OnDisconnect.removeValueAsync(): Job
expect fun OnDisconnect.cancelAsync(): Job
expect fun OnDisconnect.setValueAsync(value: Any?): Job
