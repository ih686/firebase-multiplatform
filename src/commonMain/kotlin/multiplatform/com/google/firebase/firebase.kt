package multiplatform.com.google.firebase

expect fun initializeFirebaseApp(context: Any, options: FirebaseOptions): FirebaseApp

expect class FirebaseApp

expect fun getFirebaseApps(context: Any): List<FirebaseApp>

expect class FirebaseOptions

expect class FirebaseOptionsBuilder constructor() {
    fun setApiKey(apiKey: String): FirebaseOptionsBuilder
    fun setApplicationId(applicationId: String): FirebaseOptionsBuilder
    fun setDatabaseUrl(databaseUrl: String?): FirebaseOptionsBuilder
    fun setStorageBucket(storageBucket: String?): FirebaseOptionsBuilder
    fun setProjectId(projectId: String?): FirebaseOptionsBuilder
    fun build(): FirebaseOptions
}

expect open class FirebaseException : Exception

expect open class FirebaseNetworkException : FirebaseException

expect open class FirebaseTooManyRequestsException : FirebaseException

expect open class FirebaseApiNotAvailableException : FirebaseException