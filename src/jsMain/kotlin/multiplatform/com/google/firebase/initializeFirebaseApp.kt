package multiplatform.com.google.firebase

actual fun initializeFirebaseApp(context: Any, options: FirebaseOptions): FirebaseApp {
    TODO("not implemented")
}

actual class FirebaseApp
actual class FirebaseOptions
actual class FirebaseOptionsBuilder actual constructor() {
    actual fun setApiKey(apiKey: String): FirebaseOptionsBuilder {
        TODO("not implemented")
    }

    actual fun setApplicationId(applicationId: String): FirebaseOptionsBuilder {
        TODO("not implemented")
    }

    actual fun setDatabaseUrl(databaseUrl: String?): FirebaseOptionsBuilder {
        TODO("not implemented")
    }

    actual fun setStorageBucket(storageBucket: String?): FirebaseOptionsBuilder {
        TODO("not implemented")
    }

    actual fun setProjectId(projectId: String?): FirebaseOptionsBuilder {
        TODO("not implemented")
    }

    actual fun build(): FirebaseOptions {
        TODO("not implemented")
    }
}

actual open class FirebaseException : Exception()