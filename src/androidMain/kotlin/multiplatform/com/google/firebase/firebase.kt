package multiplatform.com.google.firebase

import android.content.Context

actual typealias FirebaseException = com.google.firebase.FirebaseException

actual typealias FirebaseNetworkException = com.google.firebase.FirebaseNetworkException

actual fun initializeFirebaseApp(context: Any, options: FirebaseOptions) =
    FirebaseApp.initializeApp(context as Context, options)

actual typealias FirebaseApp = com.google.firebase.FirebaseApp

actual typealias FirebaseOptions = com.google.firebase.FirebaseOptions

actual typealias FirebaseOptionsBuilder = com.google.firebase.FirebaseOptions.Builder

actual fun getFirebaseApps(context: Any) = FirebaseApp.getApps(context as Context)

actual fun FirebaseOptionsBuilder.setApiKey(apiKey: String): FirebaseOptionsBuilder {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseOptionsBuilder.setApplicationId(applicationId: String): FirebaseOptionsBuilder {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseOptionsBuilder.setDatabaseUrl(databaseUrl: String?): FirebaseOptionsBuilder {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseOptionsBuilder.setStorageBucket(storageBucket: String?): FirebaseOptionsBuilder {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseOptionsBuilder.setProjectId(projectId: String?): FirebaseOptionsBuilder {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun FirebaseOptionsBuilder.build(): FirebaseOptions {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}