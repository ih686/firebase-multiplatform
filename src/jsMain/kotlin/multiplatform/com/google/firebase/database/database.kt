package multiplatform.com.google.firebase.database

import kotlinx.coroutines.await
import kotlin.reflect.KClass
import multiplatform.com.google.firebase.firebase

actual fun getFirebaseDatabase() = firebase.database()

actual typealias FirebaseDatabase = firebase.database.Database

actual typealias DatabaseReference = firebase.database.Reference

actual typealias DataSnapshot = firebase.database.DataSnapshot

actual typealias OnDisconnect = firebase.database.OnDisconnect

actual interface ValueEventListener {
    actual fun onDataChange(data: DataSnapshot)
    actual fun onCancelled(error: DatabaseError)
}

actual fun <T : Any> DataSnapshot.getValue(valueType: KClass<T>) = `val`() as T?

actual class DatabaseError(internal val error: Throwable)


actual val TIMESTAMP: Map<String, String>
    get() = firebase.database.ServerValue.TIMESTAMP

actual suspend fun DatabaseReference.awaitSetValue(value: Any?) = set(value).await()

actual suspend fun OnDisconnect.awaitRemoveValue() = remove().await()
actual suspend fun OnDisconnect.awaitCancel() = cancel().await()
actual suspend fun OnDisconnect.awaitSetValue(value: Any?) = set(value).await()

actual class DatabaseException(cause: Throwable) : RuntimeException(cause)

actual suspend fun DatabaseReference.awaitRemoveValue() = remove().await()

actual enum class LoggerLevel {
    DEBUG, INFO, WARN, ERROR, NONE
}

actual fun FirebaseDatabase.getReference(path: String) = ref(path)

actual fun FirebaseDatabase.setPersistenceEnabled(enabled: Boolean) {
}

actual fun FirebaseDatabase.setLogLevel(logLevel: LoggerLevel) = firebase.database.enableLogging(logLevel != LoggerLevel.NONE)

actual fun DatabaseReference.push() = firebase.database().ref().push() as DatabaseReference

actual fun DatabaseReference.onDisconnect() = onDisconnect()

actual fun DatabaseReference.addValueEventListener(listener: ValueEventListener) = on("value", { listener.onDataChange(it) }, { listener.onCancelled(DatabaseError(it)) })
        .let { listener.asDynamic().callback = it }
        .run { listener }

actual fun DatabaseReference.removeEventListener(listener: ValueEventListener) = off("value", listener.asDynamic().callback)


actual fun DatabaseError.toException() = DatabaseException(error)

actual val DataSnapshot.children: Iterable<DataSnapshot>
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun DatabaseReference.addListenerForSingleValueEvent(listener: ValueEventListener) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
