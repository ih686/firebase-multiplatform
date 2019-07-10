package multiplatform.com.google.firebase.database

import com.google.firebase.database.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Exclude
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.OnDisconnect
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

import kotlin.reflect.KClass

actual fun getFirebaseDatabase() = FirebaseDatabase.getInstance()

actual typealias LoggerLevel = Logger.Level

actual typealias FirebaseDatabase = FirebaseDatabase

actual typealias DatabaseReference = DatabaseReference

actual suspend fun DatabaseReference.awaitSetValue(value: Any?) = setValue(value).await().run { Unit }

actual suspend fun DatabaseReference.awaitUpdateChildren(update: Map<String, Any?>) = updateChildren(update).await().run { Unit }

actual typealias ValueEventListener = ValueEventListener

actual typealias DataSnapshot = DataSnapshot

actual fun <T: Any> DataSnapshot.getValue(valueType: KClass<T>) = getValue(valueType.java)

actual fun DataSnapshot.getValue(): Any? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual val DataSnapshot.children: Iterable<DataSnapshot>
    get() = TODO("not implemented")

actual typealias DatabaseException = DatabaseException

actual typealias DatabaseError = DatabaseError

actual typealias OnDisconnect = OnDisconnect

actual suspend fun OnDisconnect.awaitRemoveValue() = removeValue().await().run { Unit }

actual suspend fun OnDisconnect.awaitCancel() = cancel().await().run { Unit }

actual suspend fun OnDisconnect.awaitSetValue(value: Any?) = setValue(value).await().run { Unit }

actual suspend fun OnDisconnect.awaitUpdateChildren(update: Map<String, Any?>) = updateChildren(update).await().run { Unit }

actual val TIMESTAMP = ServerValue.TIMESTAMP

actual suspend fun DatabaseReference.awaitRemoveValue() = removeValue().await().run { Unit }

actual fun FirebaseDatabase.getReference(path: String) = getReference(path)

actual fun FirebaseDatabase.setPersistenceEnabled(enabled: Boolean) = setPersistenceEnabled(enabled)

actual fun FirebaseDatabase.setLogLevel(logLevel: LoggerLevel) = setLogLevel(logLevel)

actual fun DatabaseReference.push() = push()

actual fun DatabaseReference.onDisconnect() = onDisconnect()

actual fun DatabaseReference.addValueEventListener(listener: ValueEventListener): ValueEventListener {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DatabaseReference.removeEventListener(listener: ValueEventListener) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DatabaseError.toException(): DatabaseException {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DatabaseReference.addListenerForSingleValueEvent(listener: ValueEventListener) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun DataSnapshot.child(path: String): DataSnapshot {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual typealias Exclude = Exclude
actual typealias IgnoreExtraProperties = IgnoreExtraProperties

