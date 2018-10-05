package multiplatform.com.google.firebase

actual class FirebaseApp {
    actual companion object {
        actual fun initializeApp(context: Any, options: FirebaseOptions) {}
    }
}

actual class FirebaseOptions {
    actual class Builder {
        actual fun setApiKey(apiKey: String): Builder {
            TODO("not implemented")
        }

        actual fun setApplicationId(applicationId: String): Builder {
            TODO("not implemented")
        }

        actual fun setDatabaseUrl(databaseUrl: String?): Builder {
            TODO("not implemented")
        }

        actual fun setStorageBucket(storageBucket: String?): Builder {
            TODO("not implemented")
        }

        actual fun setProjectId(projectId: String?): Builder {
            TODO("not implemented")
        }

        actual fun build(): FirebaseOptions {
            TODO("not implemented")
        }
    }
}

actual open class FirebaseException : Exception()