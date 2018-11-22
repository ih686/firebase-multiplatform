package multiplatform.com.google.firebase.database

import kotlinx.coroutines.Job
import kotlin.reflect.KClass

expect fun getFirebaseDatabase(): FirebaseDatabase


expect class FirebaseDatabase {
    fun getReference(path: String): DatabaseReference
}

expect class DatabaseReference {
    fun push(): DatabaseReference
    fun onDisconnect(): OnDisconnect
    fun addValueEventListener(listener: ValueEventListener): ValueEventListener
}

expect fun DatabaseReference.setValue(value: Any?): Job


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

expect fun OnDisconnect.removeValue(): Job
expect fun OnDisconnect.setValue(value: Any?): Job
