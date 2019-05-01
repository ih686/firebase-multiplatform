package multiplatform.com.google.firebase

actual typealias FirebaseApp = firebase.App
typealias FirebaseError = firebase.FirebaseError

actual fun initializeFirebaseApp(context: Any, options: FirebaseOptions) =  firebase.initializeApp(options)

actual data class FirebaseOptions internal constructor(
         val apiKey: String = "",
         val applicationId: String = "",
         val databaseUrl: String? = "",
         val storageBucket: String? = "",
         val projectId: String? = "",
         val googleAppId: String = ""
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