package multiplatform.com.google.firebase.database

import kotlinx.coroutines.await
import kotlin.reflect.KClass
import multiplatform.com.google.firebase.firebase
import multiplatform.com.google.firebase.fromJson
import multiplatform.com.google.firebase.toJson
import kotlin.js.Json

actual fun getFirebaseDatabase() = firebase.database()

actual typealias FirebaseDatabase = firebase.database.Database

actual typealias DatabaseReference = firebase.database.Reference

actual typealias DataSnapshot = firebase.database.DataSnapshot

actual typealias OnDisconnect = firebase.database.OnDisconnect

actual interface ValueEventListener {
    actual fun onDataChange(data: DataSnapshot)
    actual fun onCancelled(error: DatabaseError)
}

@Suppress("UNCHECKED_CAST")
actual fun <T : Any> DataSnapshot.getValue(valueType: KClass<T>): T? = fromJson(`val`(), valueType)  as T?

actual fun DataSnapshot.getValue(): Any? = fromJson(`val`())


actual class DatabaseError(internal val error: Throwable)


actual val TIMESTAMP: Map<String, String>
    get() = firebase.database.ServerValue.TIMESTAMP

actual suspend fun DatabaseReference.awaitSetValue(value: Any?) = set(toJson(value)).await()
actual suspend fun DatabaseReference.awaitUpdateChildren(update: Map<String, Any?>) = update(toJson(update)).await()

actual suspend fun OnDisconnect.awaitRemoveValue() = remove().await()
actual suspend fun OnDisconnect.awaitCancel() = cancel().await()
actual suspend fun OnDisconnect.awaitSetValue(value: Any?) = set(value).await()
actual suspend fun OnDisconnect.awaitUpdateChildren(update: Map<String, Any?>) = update(toJson(update)).await()

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
    get() {
        val children = ArrayList<DataSnapshot>(numChildren())
        forEach {
            children.add( it.`val`() as DataSnapshot )
        }
        return children
    }

actual fun DatabaseReference.addListenerForSingleValueEvent(listener: ValueEventListener) = once("value", { listener.onDataChange(it) }, { listener.onCancelled(DatabaseError(it)) })
                .let { listener.asDynamic().callback = it }

actual fun DataSnapshot.child(path: String) = asDynamic().child(path).unsafeCast<DataSnapshot>()

actual annotation class Exclude actual constructor()
actual annotation class IgnoreExtraProperties actual constructor()
