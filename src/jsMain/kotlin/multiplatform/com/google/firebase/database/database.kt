package multiplatform.com.google.firebase.database

import kotlinx.coroutines.Deferred
import kotlin.reflect.KClass

actual fun getFirebaseDatabase(): FirebaseDatabase {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual class FirebaseDatabase {
    actual fun getReference(path: String): DatabaseReference {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    actual fun setPersistenceEnabled(enabled: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

actual class DatabaseReference {
    actual fun push(): DatabaseReference {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun onDisconnect(): OnDisconnect {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun addValueEventListener(listener: ValueEventListener): ValueEventListener {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun removeEventListener(listener: ValueEventListener) {}
}

actual interface ValueEventListener {
    actual fun onDataChange(data: DataSnapshot)
    actual fun onCancelled(error: DatabaseError)
}

actual class DataSnapshot

actual fun <T : Any> DataSnapshot.getValue(valueType: KClass<T>): T {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual class DatabaseError {

}

actual class OnDisconnect

actual val TIMESTAMP: Map<String, String>
    get() = TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

actual suspend fun DatabaseReference.awaitSetValue(value: Any?) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual suspend fun OnDisconnect.awaitRemoveValue() {}
actual suspend fun OnDisconnect.awaitCancel() {}

actual suspend fun OnDisconnect.awaitSetValue(value: Any?) {}