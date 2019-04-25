package multiplatform.com.google.firebase.database

import kotlinx.coroutines.await
import kotlin.js.Promise
import kotlin.reflect.KClass

@JsModule("firebase")
external val firebase: dynamic

@JsModule("firebase")
external val database: dynamic

@JsModule("firebase/database")
external fun enableLogging(logger: Boolean?, persistent: Boolean? = definedExternally)

actual fun getFirebaseDatabase() = firebase.database()

@JsModule("firebase/database")
open external class Database {
    fun ref(path: String?): DatabaseReference
}

actual typealias FirebaseDatabase = Database

@JsModule("firebase/database")
open external class Reference {
    fun remove(): Promise<Unit>
    fun onDisconnect(): OnDisconnect
    fun set(value: Any? = definedExternally): Promise<Unit>
    fun on(eventType: String?, callback: (data: DataSnapshot) -> Unit, cancelCallbackOrContext: (error: Throwable) -> Unit? = definedExternally, context: Any? = definedExternally): (DataSnapshot) -> Unit
    fun off(eventType: String?, callback: (data: DataSnapshot) -> Unit, context: Any? = definedExternally)
}


actual typealias DatabaseReference = Reference

actual interface ValueEventListener {
    actual fun onDataChange(data: DataSnapshot)
    actual fun onCancelled(error: DatabaseError)
}

@JsModule("firebase/database")
actual external class DataSnapshot {
    fun `val`(): Any
}


actual fun <T : Any> DataSnapshot.getValue(valueType: KClass<T>) = `val`() as T?

actual class DatabaseError(internal val error: Throwable)

@JsModule("firebase/database")
actual open external class OnDisconnect {
    fun remove(): Promise<Unit>
    fun cancel(): Promise<Unit>
    fun set(value: Any?): Promise<Unit>
}


actual val TIMESTAMP: Map<String, String>
    get() = firebase.database.ServerValue.TIMESTAMP as Map<String, String>

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

actual fun FirebaseDatabase.setLogLevel(logLevel: LoggerLevel) = enableLogging(logLevel != LoggerLevel.NONE)

actual fun DatabaseReference.push() = getFirebaseDatabase().ref().push()

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
