package multiplatform.com.google.firebase

import kotlin.js.Json
import kotlin.js.json
import kotlin.reflect.KClass

actual typealias FirebaseApp = firebase.App

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

actual open class FirebaseException(code: String?, message: String?) : Exception("$code: $message")

actual fun getFirebaseApps(context: Any) = firebase.apps.toList()

actual open class FirebaseNetworkException(code: String?, message: String?) : FirebaseException(code, message)
actual open class FirebaseTooManyRequestsException(code: String?, message: String?) : FirebaseException(code, message)
actual open class FirebaseApiNotAvailableException(code: String?, message: String?) : FirebaseException(code, message)

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


