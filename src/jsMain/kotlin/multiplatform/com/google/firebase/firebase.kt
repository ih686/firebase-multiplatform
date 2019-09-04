package multiplatform.com.google.firebase

import multiplatform.com.google.firebase.auth.FirebaseAuthException
import multiplatform.com.google.firebase.auth.FirebaseAuthInvalidUserException
import multiplatform.com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import multiplatform.com.google.firebase.auth.FirebaseAuthWebException
import multiplatform.com.google.firebase.firestore.FirebaseFirestoreException
import multiplatform.com.google.firebase.firestore.FirestoreExceptionCode
import multiplatform.com.google.firebase.functions.FirebaseFunctionsException
import kotlin.js.Json
import kotlin.js.json
import kotlin.reflect.KClass

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


internal suspend fun <T, R> T.translateException(function: suspend T.() -> R): R {
    var exception: Exception?

    try {
        return function()
    } catch(e: dynamic) {
        if(e.code) {
            throw when {
                e.code === "auth/app-deleted" -> FirebaseAuthException(e.code as String, e.message as String)
                e.code === "auth/app-not-authorized" -> FirebaseAuthException(e.code as String, e.message as String)
                e.code === "auth/argument-error" -> FirebaseAuthException(e.code as String, e.message as String)
                e.code === "auth/invalid-api-key" -> FirebaseAuthException(e.code as String, e.message as String)
                e.code === "auth/invalid-user-token" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                e.code === "auth/network-request-failed" -> FirebaseAuthException(e.code as String, e.message as String)
                e.code === "auth/operation-not-allowed" -> FirebaseAuthException(e.code as String, e.message as String)
                e.code === "auth/requires-recent-login" -> FirebaseAuthRecentLoginRequiredException(e.code as String, e.message as String)
                e.code === "auth/too-many-arguments" -> FirebaseAuthException(e.code as String, e.message as String)
                e.code === "auth/unauthorized-domain" -> FirebaseAuthException(e.code as String, e.message as String)
                e.code === "auth/user-disabled" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                e.code === "auth/user-token-expired" -> FirebaseAuthInvalidUserException(e.code as String, e.message as String)
                e.code === "auth/web-storage-unsupported" -> FirebaseAuthWebException(e.code as String, e.message as String)

                e.code === "cancelled" && e.name === "FirebaseError"  -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.CANCELLED)
                e.code === "unknown" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.UNKNOWN)
                e.code === "invalid-argument" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.INVALID_ARGUMENT)
                e.code === "deadline-exceeded" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.DEADLINE_EXCEEDED)
                e.code === "not-found" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.NOT_FOUND)
                e.code === "already-exists" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.ALREADY_EXISTS)
                e.code === "permission-denied" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.PERMISSION_DENIED)
                e.code === "resource-exhausted" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.RESOURCE_EXHAUSTED)
                e.code === "failed-precondition" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.FAILED_PRECONDITION)
                e.code === "aborted" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.ABORTED)
                e.code === "out-of-range" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.OUT_OF_RANGE)
                e.code === "unimplemented" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.UNIMPLEMENTED)
                e.code === "internal" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.INTERNAL)
                e.code === "unavailable" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.UNAVAILABLE)
                e.code === "data-loss" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.DATA_LOSS)
                e.code === "unauthenticated" && e.name === "FirebaseError" -> FirebaseFirestoreException(e.message as String, FirestoreExceptionCode.UNAUTHENTICATED)

                e.code === "cancelled" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "unknown" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "invalid-argument" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "deadline-exceeded" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "not-found" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "already-exists" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "permission-denied" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "unauthenticated" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "resource-exhausted" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "failed-precondition" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "aborted" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "out-of-range" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "unimplemented" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "internal" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "unavailable" -> FirebaseFunctionsException(e.message as String, e.code as String)
                e.code === "data-loss" -> FirebaseFunctionsException(e.message as String, e.code as String)

                else -> FirebaseException(e)
            }
        } else {
            exception = FirebaseException(e)
        }
    }
    throw exception!!
}