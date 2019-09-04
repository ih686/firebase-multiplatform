package multiplatform.com.google.firebase

import multiplatform.com.google.firebase.auth.FirebaseAuthException
import multiplatform.com.google.firebase.auth.FirebaseAuthInvalidUserException
import multiplatform.com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import multiplatform.com.google.firebase.auth.FirebaseAuthWebException
import multiplatform.com.google.firebase.database.DatabaseError
import multiplatform.com.google.firebase.database.DatabaseException
import multiplatform.com.google.firebase.database.FirebaseDatabase
import multiplatform.com.google.firebase.firestore.FirebaseFirestoreException
import multiplatform.com.google.firebase.firestore.FirestoreExceptionCode
import kotlin.js.Json
import kotlin.js.json
import kotlin.math.log
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

actual typealias FirebaseApp = firebase.App

typealias FirebaseError = Error

actual fun initializeFirebaseApp(context: Any, options: FirebaseOptions) =  firebase.initializeApp(
        json(
            "apiKey" to options.apiKey,
            "applicationId" to options.applicationId,
            "databaseURL" to options.databaseUrl,
            "storageBucket" to options.storageBucket,
            "projectId" to options.projectId,
            "googleAppId" to options.googleAppId
        )
)

actual data class FirebaseOptions internal constructor(
         val apiKey: String? = undefined,
         val applicationId: String? = undefined,
         val databaseUrl: String? = undefined,
         val storageBucket: String? = undefined,
         val projectId: String? = undefined,
         val googleAppId: String? = undefined
)

actual class FirebaseOptionsBuilder actual constructor() {
    internal var options = FirebaseOptions()
}

fun FirebaseError.toFirebaseException() = FirebaseException(this)

actual open class FirebaseException(error: FirebaseError) : Exception(error)

actual fun getFirebaseApps(context: Any) = firebase.apps.toList()

actual open class FirebaseNetworkException(error: FirebaseError) : FirebaseException(error)
actual open class FirebaseTooManyRequestsException(error: FirebaseError) : FirebaseException(error)

actual open class FirebaseApiNotAvailableException(error: FirebaseError) : FirebaseException(error)

actual fun FirebaseOptionsBuilder.setGoogleAppId(googleAppId: String) = options.copy(googleAppId = googleAppId).let { options = it }.let { this }

actual fun FirebaseOptionsBuilder.setApiKey(apiKey: String) = options.copy(apiKey = apiKey).let { options = it }.let { this }

actual fun FirebaseOptionsBuilder.setApplicationId(applicationId: String) = options.copy(applicationId = applicationId).let { options = it }.let { this }

actual fun FirebaseOptionsBuilder.setDatabaseUrl(databaseUrl: String?) = options.copy(databaseUrl = databaseUrl).let { options = it }.let { this }

actual fun FirebaseOptionsBuilder.setStorageBucket(storageBucket: String?) = options.copy(storageBucket = storageBucket).let { options = it }.let { this }

actual fun FirebaseOptionsBuilder.setProjectId(projectId: String?) = options.copy(projectId = projectId).let { options = it }.let { this }

actual fun FirebaseOptionsBuilder.build() = options

internal fun toJson(data: Any?): Any? = when(data) {
    null -> null
    is firebase.firestore.FieldValue -> data
    is Boolean -> data
    is Double -> data
    is String -> data
    is List<*> -> data.map { toJson(it) }.toTypedArray()
    is Map<*, *> -> json(*data.entries.map { (k, v) -> k as String to toJson(v) }.toTypedArray())
    else -> (js("Object").entries(data) as Array<Array<Any>>)
            .filter { (key) -> !key.toString().startsWith("__exclude__") }
            .map { (key, value) ->
                key as String
                val unmangled = key.substringBefore("_")
                if(data.asDynamic().__proto__.hasOwnProperty(unmangled).unsafeCast<Boolean>()) {
                    unmangled to toJson(value)
                } else {
                    key to toJson(value)
                }
            }
            .let { json(*it.toTypedArray()) }
}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
internal fun fromJson(data: Any?, valueType: KClass<*>? = null): Any? = when(data) {
    undefined -> undefined
    is Boolean -> data
    is Double -> data
    is String -> data
    is Array<*> -> data.map { fromJson(it) }
    else -> {
        if(valueType == null || valueType == Map::class) (js("Object").entries(data) as Array<Array<Any>>)
                .associate { (key, value) -> key to fromJson(value) }
                .let { return@fromJson it }

        val instance = js("Reflect").construct(valueType.js, emptyArray<Any>())

        val mangled = js("Object")
                .keys(instance)
                .unsafeCast<Array<String>>()
                .associate { it.substringBefore("_") to it }

        (js("Object").entries(data) as Array<Array<Any>>)
                .forEach { (key, value) ->
                    val descriptor = js("Object").getOwnPropertyDescriptor(instance.__proto__, key)
                    if(descriptor == null) {
                        instance[key as String] = fromJson(value)
                    } else {
                        instance[mangled[key as String]] = fromJson(value)
                    }
                }

        instance.unsafeCast<Json>()
   }
}


internal suspend fun <T, R> T.runActualWithHandler(function: suspend T.() -> R): R {
    var exception: Exception?

    try {
        return function()
    } catch(e: dynamic) {
        if(e.code) {
            throw when(e.code) {
                "auth/app-deleted" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/app-not-authorized" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/argument-error" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/invalid-api-key" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/invalid-user-token" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                "auth/network-request-failed" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/operation-not-allowed" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/requires-recent-login" -> FirebaseAuthRecentLoginRequiredException(e.code as String, e.message as String)
                "auth/too-many-arguments" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/unauthorized-domain" -> FirebaseAuthException(e.code as String, e.message as String)
                "auth/user-disabled" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                "auth/user-token-expired" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                "auth/web-storage-unsupported" -> FirebaseAuthWebException(e.code as String, e.message as String)

                "cancelled" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.CANCELLED)
                "unknown" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.UNKNOWN)
                "invalid-argument" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.INVALID_ARGUMENT)
                "deadline-exceeded" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.DEADLINE_EXCEEDED)
                "not-found" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.NOT_FOUND)
                "already-exists" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.ALREADY_EXISTS)
                "permission-denied" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.PERMISSION_DENIED)
                "resource-exhausted" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.RESOURCE_EXHAUSTED)
                "failed-precondition" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.FAILED_PRECONDITION)
                "aborted" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.ABORTED)
                "out-of-range" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.OUT_OF_RANGE)
                "unimplemented" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.UNIMPLEMENTED)
                "internal" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.INTERNAL)
                "unavailable" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.UNAVAILABLE)
                "data-loss" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.DATA_LOSS)
                "unauthenticated" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.UNAUTHENTICATED)

                else -> FirebaseException(e)
            }
        } else if(e is DatabaseException) {
            exception = e
        } else {
            exception = FirebaseException(e)
        }
    }
    throw exception!!
}