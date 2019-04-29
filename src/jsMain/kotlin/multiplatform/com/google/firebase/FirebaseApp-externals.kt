
package multiplatform.com.google.firebase

import kotlin.js.Promise

@JsModule("firebase")
external object firebase {

    open class App
    val apps : List<App>
    fun initializeApp(options: Any, name: String? = definedExternally) : App

    open class FirebaseError : Throwable {
        var code: String
        override var message: String
    }

    // AUTH
    fun auth(): auth.Auth
    object auth {
        open class Auth {
            val currentUser: user.User?

            fun signInWithCustomToken(token: String): Promise<AuthResult>
            fun signInAnonymously(): Promise<AuthResult>
            fun signOut(): Promise<Unit>

            fun onAuthStateChanged(nextOrObserver: Any): () -> Unit

            var unsubscribe: () -> Unit
        }
        interface AuthResult {
            val user: user.User
        }
    }

    // USER
    fun User(a: Any,b: Any,c: Any): user.User
    object user {
        open abstract class User {
            val uid: String
            val isAnonymous: Boolean

            fun delete(): Promise<Unit>
            fun reload(): Promise<Unit>
        }
    }

    // DATABASE
    fun database(): database.Database
    object database {
        fun enableLogging(logger: Boolean?, persistent: Boolean? = definedExternally)
        open class Database {
            fun ref(path: String? = definedExternally): Reference
        }
        open class ThenableReference

        open class Reference {
            fun remove(): Promise<Unit>
            fun onDisconnect(): OnDisconnect
            fun set(value: Any? = definedExternally): Promise<Unit>
            fun on(eventType: String?, callback: (data: DataSnapshot) -> Unit, cancelCallbackOrContext: (error: Throwable) -> Unit? = definedExternally, context: Any? = definedExternally): (DataSnapshot) -> Unit
            fun off(eventType: String?, callback: (data: DataSnapshot) -> Unit, context: Any? = definedExternally)
            fun push(): ThenableReference
        }
        open class DataSnapshot {
            fun `val`(): Any
        }

        open class OnDisconnect {
            fun remove(): Promise<Unit>
            fun cancel(): Promise<Unit>
            fun set(value: Any?): Promise<Unit>
        }

        object ServerValue {
            val TIMESTAMP: Map<String, String>
        }
    }

    // FUNCTIONS
    fun functions(app: App? = definedExternally): functions.Functions
    object functions {
        open class Functions {
            fun httpsCallable(name: String): HttpsCallable
        }
        open class HttpsCallableResult
        open class HttpsCallable {
            fun __call(data: Any? = definedExternally): Promise<HttpsCallableResult>
        }

    }

    // FIRESTORE
    fun firestore(): firestore.Firestore
    object firestore {
        fun setLogLevel(level: String)
        open class Firestore {
            fun <T> runTransaction(func: (transaction: Transaction) -> Promise<T>): Promise<T>
            fun batch(): WriteBatch
            fun collection(collectionPath: String): CollectionReference
            fun doc(documentPath: String): DocumentReference
        }

        open class FieldPath internal constructor(fieldNames: Array<out String>)

        open class Query {
            fun get(options: Any? = definedExternally): Promise<QuerySnapshot>
            fun where(fieldPath: Any, opStr: String, value: Any?): Query
        }

        open class CollectionReference : Query {
            fun add(data: Any): Promise<DocumentReference>
        }

        open class QuerySnapshot {
            val docs: List<DocumentSnapshot>
        }

        open class DocumentSnapshot {
            val id: String
            val exists: Boolean
            fun get(fieldPath: Any, options: Any? = definedExternally): Any?
        }

        interface ListenerRegistration

        open class DocumentReference {
            val id: String

            fun get(options: Any? = definedExternally): Promise<DocumentSnapshot>
            fun set(data: Any, options: Any? = definedExternally): Promise<Unit>
            fun update(data: Any): Promise<Unit>
            fun delete(): Promise<Unit>
            fun onSnapshot(observer: Any): ListenerRegistration
        }

        open class WriteBatch {
            fun commit(): Promise<Unit>
            fun delete(documentReference: DocumentReference): multiplatform.com.google.firebase.firestore.WriteBatch
            fun set(documentReference: DocumentReference, data: Any, options: Any? = definedExternally): multiplatform.com.google.firebase.firestore.WriteBatch
            fun update(documentReference: DocumentReference, data: Any): multiplatform.com.google.firebase.firestore.WriteBatch
            fun update(documentReference: DocumentReference, field: Any, value: Any?, vararg moreFieldsAndValues: Array<out Any>): multiplatform.com.google.firebase.firestore.WriteBatch
        }

        open class Transaction {
            fun get(documentRefence: DocumentReference): Promise<DocumentSnapshot>
            fun set(documentReference: DocumentReference, data: Any, options: Any? = definedExternally): Transaction
            fun update(documentReference: DocumentReference, data: Any): Transaction
            fun update(documentReference: DocumentReference, field: Any, value: Any?, vararg moreFieldsAndValues: Array<out Any>): Transaction
            fun delete(documentReference: DocumentReference): Transaction
        }

        open class SetOptions {
            companion object {
                var merge: Boolean
            }
        }
    }
}