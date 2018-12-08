package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.asDeferred
import kotlin.reflect.KClass

actual fun getFirebaseFirestore() = FirebaseFirestore.getInstance()

actual typealias FirebaseFirestore = com.google.firebase.firestore.FirebaseFirestore

actual fun <T> FirebaseFirestore.runTransactionAsync(func: (transaction: Transaction) -> T) = runTransaction(func).asDeferred()

actual typealias Transaction = com.google.firebase.firestore.Transaction

actual fun DocumentReference.setAsync(data: Map<String, Any>): Job = set(data).asDeferred()

actual fun DocumentReference.setAsync(pojo: Any): Job = set(pojo).asDeferred()

actual fun DocumentReference.setAsync(data: Map<String, Any>, options: SetOptions): Job = set(data, options).asDeferred()

actual fun DocumentReference.setAsync(pojo: Any, options: SetOptions): Job = set(pojo, options).asDeferred()

actual fun DocumentReference.updateAsync(data: Map<String, Any>): Job = update(data).asDeferred()

actual fun DocumentReference.addSnapshotListener(listener: (snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) -> Unit) = addSnapshotListener { s, e -> listener(s, e) }

actual typealias CollectionReference = com.google.firebase.firestore.CollectionReference

actual fun CollectionReference.addAsync(data: Map<String, Any>) = add(data).asDeferred()

actual fun CollectionReference.addAsync(pojo: Any) = add(pojo).asDeferred()

actual typealias FirebaseFirestoreException = com.google.firebase.firestore.FirebaseFirestoreException

actual typealias QuerySnapshot =  com.google.firebase.firestore.QuerySnapshot

actual val QuerySnapshot.documents: List<DocumentSnapshot>
    get() = documents

actual typealias DocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot

actual val DocumentSnapshot.id: String
    get() = id

actual fun <T : Any> DocumentSnapshot.toObject(valueType: KClass<T>) = toObject(valueType.java) as T

actual typealias EventListener<T> = com.google.firebase.firestore.EventListener<T>

actual typealias ListenerRegistration = com.google.firebase.firestore.ListenerRegistration

actual typealias Query = com.google.firebase.firestore.Query

actual fun Query.getAsync() = get().asDeferred()

actual fun Query.addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit) = addSnapshotListener { s, e -> listener(s, e) }

actual typealias FieldPath = com.google.firebase.firestore.FieldPath

actual fun fieldPathOf(vararg fieldNames: String) = FieldPath.of(*fieldNames)

actual typealias FirebaseFirestoreSettings = com.google.firebase.firestore.FirebaseFirestoreSettings

actual typealias FirebaseFirestoreSettingsBuilder =  com.google.firebase.firestore.FirebaseFirestoreSettings.Builder

actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference

actual typealias SetOptions = com.google.firebase.firestore.SetOptions

actual fun mergeSetOptions(): SetOptions = SetOptions.merge()

actual fun DocumentReference.deleteAsync(): Job = delete().asDeferred()

actual val DocumentReference.id: String
    get() = id

actual typealias WriteBatch = com.google.firebase.firestore.WriteBatch

actual fun WriteBatch.commitAsync(): Job = commit().asDeferred()

actual fun DocumentReference.getAsync(): Deferred<DocumentSnapshot> = get().asDeferred()