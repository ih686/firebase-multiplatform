package multiplatform.com.google.firebase.database

import kotlin.reflect.KClass

actual fun getFirebaseDatabase(): FirebaseDatabase {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual class FirebaseDatabase

actual class DatabaseReference

actual interface ValueEventListener {
    actual fun onDataChange(data: DataSnapshot)
    actual fun onCancelled(error: DatabaseError)
}

actual class DataSnapshot

actual fun <T : Any> DataSnapshot.getValue(valueType: KClass<T>): T? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual class DatabaseError

actual class OnDisconnect

actual val TIMESTAMP: Map<String, String>
    get() = TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

actual suspend fun DatabaseReference.awaitSetValue(value: Any?) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun OnDisconnect.awaitRemoveValue() {}
actual suspend fun OnDisconnect.awaitCancel() {}

actual suspend fun OnDisconnect.awaitSetValue(value: Any?) {}
actual class DatabaseException : RuntimeException()

actual suspend fun DatabaseReference.awaitRemoveValue() {}
actual enum class LoggerLevel {
    DEBUG, INFO, WARN, ERROR, NONE
}

actual fun FirebaseDatabase.getReference(path: String): DatabaseReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseDatabase.setPersistenceEnabled(enabled: Boolean) {
}

actual fun FirebaseDatabase.setLogLevel(logLevel: LoggerLevel) {
}

actual fun DatabaseReference.push(): DatabaseReference {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DatabaseReference.onDisconnect(): OnDisconnect {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DatabaseReference.addValueEventListener(listener: ValueEventListener): ValueEventListener {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DatabaseReference.removeEventListener(listener: ValueEventListener) {
}

actual fun DatabaseError.toException(): DatabaseException {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual val DataSnapshot.children: Iterable<DataSnapshot>
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

actual fun DatabaseReference.addListenerForSingleValueEvent(listener: ValueEventListener) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}