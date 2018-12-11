package multiplatform.com.google.firebase.database

import com.google.firebase.database.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.OnDisconnect
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.tasks.asDeferred
import kotlin.reflect.KClass

actual fun getFirebaseDatabase() = FirebaseDatabase.getInstance()

actual typealias FirebaseDatabase = FirebaseDatabase

actual typealias DatabaseReference = DatabaseReference

actual fun DatabaseReference.setValueAsync(value: Any?): Deferred<*> = setValue(value).asDeferred()

actual typealias ValueEventListener = ValueEventListener

actual typealias DataSnapshot = DataSnapshot

actual fun <T: Any> DataSnapshot.getValue(valueType: KClass<T>) = getValue(valueType.java) as T

actual typealias DatabaseError = DatabaseError

actual typealias OnDisconnect = OnDisconnect

actual fun OnDisconnect.removeValueAsync(): Deferred<*> = removeValue().asDeferred()

actual fun OnDisconnect.cancelAsync(): Deferred<*> = cancel().asDeferred()

actual fun OnDisconnect.setValueAsync(value: Any?): Deferred<*> = setValue(value).asDeferred()

actual val TIMESTAMP = ServerValue.TIMESTAMP

