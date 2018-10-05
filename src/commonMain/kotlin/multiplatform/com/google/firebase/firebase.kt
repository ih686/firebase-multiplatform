package multiplatform.com.google.firebase

expect class FirebaseApp {
    companion object {
        fun initializeApp(context: Any, options: FirebaseOptions)
    }
}

expect class FirebaseOptions {
    class Builder constructor() {
        fun setApiKey(apiKey: String): FirebaseOptions.Builder
        fun setApplicationId(applicationId: String): FirebaseOptions.Builder
        fun setDatabaseUrl(databaseUrl: String?): FirebaseOptions.Builder
        fun setStorageBucket(storageBucket: String?): FirebaseOptions.Builder
        fun setProjectId(projectId: String?): FirebaseOptions.Builder
        fun build(): FirebaseOptions
    }
}

expect open class FirebaseException : Exception