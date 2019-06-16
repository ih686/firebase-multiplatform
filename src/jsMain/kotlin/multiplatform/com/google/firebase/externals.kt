
package multiplatform.com.google.firebase

import kotlin.js.Json
import kotlin.js.Promise

@JsModule("firebase")
external object firebase {

    open class App
    val apps : Array<App>
    fun initializeApp(options: Any, name: String? = definedExternally) : App

    open interface FirebaseError {
        var code: String
        var message: String
        var name: String
    }

    // AUTH
    fun auth(): auth.Auth
    object auth {
        open class Auth {
            val currentUser: user.User?

            fun signInWithCustomToken(token: String): Promise<AuthResult>
            fun signInAnonymously(): Promise<AuthResult>
            fun signOut(): Promise<Unit>

            fun onAuthStateChanged(nextOrObserver: (user.User) -> Unit): () -> Unit
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

            fun update(value: Any?): Promise<Unit>
            fun set(value: Any?): Promise<Unit>
            fun on(eventType: String?, callback: (data: DataSnapshot) -> Unit, cancelCallbackOrContext: (error: Throwable) -> Unit? = definedExternally, context: Any? = definedExternally): (DataSnapshot) -> Unit
            fun off(eventType: String?, callback: (data: DataSnapshot) -> Unit, context: Any? = definedExternally)
            fun once(eventType: String, callback: (data: DataSnapshot) -> Unit, failureCallbackOrContext: (error: Throwable) -> Unit? = definedExternally, context: Any? = definedExternally): (DataSnapshot)->Unit
            fun push(): ThenableReference
        }
        open class DataSnapshot {
            fun `val`(): Any
            fun forEach(action: (a: DataSnapshot)-> Boolean): Boolean
            fun numChildren(): Int
        }

        open class OnDisconnect {
            fun update(value: Any?): Promise<Unit>
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
        class Functions {
            fun httpsCallable(name: String): HttpsCallable
        }
        class HttpsCallableResult {
//            val data: Any
        }
        class HttpsCallable

    }

    // FIRESTORE
    fun firestore(): firestore.Firestore
    object firestore {
        fun setLogLevel(level: String)

        open class PersistenceSettings {
            var experimentalTabSynchronization: Boolean
        }

        open class Firestore {
            var _th_settings: dynamic
            fun <T> runTransaction(func: (transaction: Transaction) -> Promise<T>): Promise<T>
            fun batch(): WriteBatch
            fun collection(collectionPath: String): CollectionReference
            fun doc(documentPath: String): DocumentReference
            fun settings(settings: Json)
            fun enablePersistence(): Promise<Unit>
        }

        open class FieldPath internal constructor(fieldNames: Array<out String>)

        open class Query {
            fun get(options: Any? = definedExternally): Promise<QuerySnapshot>
            fun where(fieldPath: Any, opStr: String, value: Any?): Query
            fun onSnapshot(next: (snapshot: QuerySnapshot) ->Unit, error: (error: Error) -> Unit): ()->Unit
        }

        open class CollectionReference : Query {
            fun add(data: Any): Promise<DocumentReference>
        }

        open class QuerySnapshot {
            val docs: Array<DocumentSnapshot>
            val empty: Boolean
        }

        open class DocumentSnapshot {
            val id: String
            val exists: Boolean
            fun data(options: Any? = definedExternally): Any?
            fun get(fieldPath: Any, options: Any? = definedExternally): Any?
        }

        open class DocumentReference {
            val id: String

            fun get(options: Any? = definedExternally): Promise<DocumentSnapshot>
            fun set(data: Any, options: Any? = definedExternally): Promise<Unit>
            fun update(data: Any): Promise<Unit>
//            fun update(field: Any, value: Any?, vararg moreFieldsAndValues: Any?): Promise<Unit>
            fun delete(): Promise<Unit>
            fun onSnapshot(next: (snapshot: DocumentSnapshot) ->Unit, error: (error: Error) -> Unit): ()->Unit
        }

        open class WriteBatch {
            fun commit(): Promise<Unit>
//            fun delete(documentReference: DocumentReference): WriteBatch
//            fun set(documentReference: DocumentReference, data: Any, options: Any? = definedExternally): WriteBatch
//            fun update(documentReference: DocumentReference, data: Any): WriteBatch
//            fun update(documentReference: DocumentReference, field: Any, value: Any?, vararg moreFieldsAndValues: Any): WriteBatch
        }

        open class Transaction {
            fun get(documentRefence: DocumentReference): Promise<DocumentSnapshot>
//            fun set(documentReference: DocumentReference, data: Any, options: Any? = definedExternally): Transaction
//            fun update(documentReference: DocumentReference, data: Any): Transaction
//            fun update(documentReference: DocumentReference, field: Any, value: Any?, vararg moreFieldsAndValues: Any): Transaction
//            fun delete(documentReference: DocumentReference): Transaction
        }

        open abstract class FieldValue {
            companion object {
                fun delete(): FieldValue
                fun arrayRemove(vararg elements: Any): FieldValue
                fun arrayUnion(vararg elements: Any): FieldValue
            }
        }
    }
}