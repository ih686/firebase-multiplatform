package multiplatform.com.google.firebase.database

import kotlin.reflect.KClass

expect fun getFirebaseDatabase(): FirebaseDatabase

expect enum class LoggerLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    NONE
}

expect class FirebaseDatabase {
    fun getReference(path: String): DatabaseReference
    fun setPersistenceEnabled(enabled: Boolean)
    fun setLogLevel(logLevel: LoggerLevel)
}

expect class DatabaseReference {
    fun push(): DatabaseReference
    fun onDisconnect(): OnDisconnect
    fun addValueEventListener(listener: ValueEventListener): ValueEventListener
    fun removeEventListener(listener: ValueEventListener)
}

expect suspend fun DatabaseReference.awaitSetValue(value: Any?)
expect suspend fun DatabaseReference.awaitRemoveValue()

expect interface ValueEventListener {
    fun onDataChange(data: DataSnapshot)
    fun onCancelled(error: DatabaseError)
}

expect class DataSnapshot

expect fun <T: Any> DataSnapshot.getValue(valueType: KClass<T>): T?

expect val TIMESTAMP: Map<String, String>

expect class DatabaseException : RuntimeException

expect class DatabaseError {
    fun toException(): DatabaseException
}

expect class OnDisconnect

expect suspend fun OnDisconnect.awaitRemoveValue()
expect suspend fun OnDisconnect.awaitCancel()
expect suspend fun OnDisconnect.awaitSetValue(value: Any?)
