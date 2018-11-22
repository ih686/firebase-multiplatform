package multiplatform.com.google.firebase.database

import com.google.firebase.database.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.OnDisconnect
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.tasks.asDeferred
import kotlin.reflect.KClass

actual fun getFirebaseDatabase() = FirebaseDatabase.getInstance()

actual typealias FirebaseDatabase = FirebaseDatabase

actual typealias DatabaseReference = DatabaseReference

actual fun DatabaseReference.setValue(value: Any?): Job = setValue(value).asDeferred()

actual typealias ValueEventListener = ValueEventListener

actual typealias DataSnapshot = DataSnapshot

actual fun <T: Any> DataSnapshot.getValue(valueType: KClass<T>) = getValue(valueType.java) as T

actual typealias DatabaseError = DatabaseError

actual typealias OnDisconnect = OnDisconnect

actual fun OnDisconnect.removeValue(): Job = removeValue().asDeferred()

actual fun OnDisconnect.setValue(value: Any?): Job = setValue(value).asDeferred()

actual val TIMESTAMP = ServerValue.TIMESTAMP

