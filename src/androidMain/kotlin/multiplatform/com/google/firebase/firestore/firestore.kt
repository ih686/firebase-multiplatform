package multiplatform.com.google.firebase.firestore

import kotlinx.coroutines.tasks.await
import kotlin.reflect.KClass

actual fun getFirebaseFirestore() = FirebaseFirestore.getInstance()

actual typealias FirebaseFirestore = com.google.firebase.firestore.FirebaseFirestore

actual suspend fun <T> FirebaseFirestore.awaitRunTransaction(func: (transaction: Transaction) -> T) = runTransaction(func).await()

actual typealias Transaction = com.google.firebase.firestore.Transaction

actual suspend fun DocumentReference.awaitSet(data: Map<String, Any>) = set(data).await().run { Unit }

actual suspend fun DocumentReference.awaitSet(pojo: Any) = set(pojo).await().run { Unit }

actual suspend fun DocumentReference.awaitSet(data: Map<String, Any>, options: SetOptions) = set(data, options).await().run { Unit }

actual suspend fun DocumentReference.awaitSet(pojo: Any, options: SetOptions) = set(pojo, options).await().run { Unit }

actual suspend fun DocumentReference.awaitUpdate(data: Map<String, Any>) = update(data).await().run { Unit }

actual fun DocumentReference.addSnapshotListener(listener: (snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) -> Unit) = addSnapshotListener { s, e -> listener(s, e) }

actual typealias CollectionReference = com.google.firebase.firestore.CollectionReference

actual suspend fun CollectionReference.awaitAdd(data: Map<String, Any>) = add(data).await()

actual suspend fun CollectionReference.awaitAdd(pojo: Any) = add(pojo).await()

actual typealias FirebaseFirestoreException = com.google.firebase.firestore.FirebaseFirestoreException

actual val FirebaseFirestoreException.code: FirestoreExceptionCode get() = code

actual typealias FirestoreExceptionCode = com.google.firebase.firestore.FirebaseFirestoreException.Code

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

actual suspend fun Query.awaitGet() = get().await()

actual fun Query.addSnapshotListener(listener: (snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) -> Unit) = addSnapshotListener { s, e -> listener(s, e) }

actual typealias FieldPath = com.google.firebase.firestore.FieldPath

actual fun fieldPathOf(vararg fieldNames: String) = FieldPath.of(*fieldNames)

actual typealias FirebaseFirestoreSettings = com.google.firebase.firestore.FirebaseFirestoreSettings

actual typealias FirebaseFirestoreSettingsBuilder =  com.google.firebase.firestore.FirebaseFirestoreSettings.Builder

actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference

actual typealias SetOptions = com.google.firebase.firestore.SetOptions

actual fun mergeSetOptions(): SetOptions = SetOptions.merge()

actual suspend fun DocumentReference.awaitDelete() = delete().await().run { Unit }

actual val DocumentReference.id: String
    get() = id

actual typealias WriteBatch = com.google.firebase.firestore.WriteBatch

actual suspend fun WriteBatch.awaitCommit() = commit().await().run { Unit }

actual suspend fun DocumentReference.awaitGet() = get().await()