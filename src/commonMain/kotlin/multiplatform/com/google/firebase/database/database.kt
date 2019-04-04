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

expect class FirebaseDatabase

expect fun FirebaseDatabase.getReference(path: String): DatabaseReference
expect fun FirebaseDatabase.setPersistenceEnabled(enabled: Boolean)
expect fun FirebaseDatabase.setLogLevel(logLevel: LoggerLevel)

expect class DatabaseReference

expect fun DatabaseReference.push(): DatabaseReference
expect fun DatabaseReference.onDisconnect(): OnDisconnect
expect fun DatabaseReference.addValueEventListener(listener: ValueEventListener): ValueEventListener
expect fun DatabaseReference.removeEventListener(listener: ValueEventListener)

expect suspend fun DatabaseReference.awaitSetValue(value: Any?)
expect suspend fun DatabaseReference.awaitRemoveValue()

expect interface ValueEventListener

expect fun ValueEventListener.onDataChange(data: DataSnapshot)
expect fun ValueEventListener.onCancelled(error: DatabaseError)

expect class DataSnapshot

expect fun <T: Any> DataSnapshot.getValue(valueType: KClass<T>): T?

expect val TIMESTAMP: Map<String, String>

expect class DatabaseException : RuntimeException

expect class DatabaseError

expect fun DatabaseError.toException(): DatabaseException

expect class OnDisconnect

expect suspend fun OnDisconnect.awaitRemoveValue()
expect suspend fun OnDisconnect.awaitCancel()
expect suspend fun OnDisconnect.awaitSetValue(value: Any?)
