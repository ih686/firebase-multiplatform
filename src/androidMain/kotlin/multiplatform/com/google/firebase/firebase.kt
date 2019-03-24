package multiplatform.com.google.firebase

import android.content.Context

actual typealias FirebaseException = com.google.firebase.FirebaseException

actual typealias FirebaseNetworkException = com.google.firebase.FirebaseNetworkException

actual typealias FirebaseTooManyRequestsException = com.google.firebase.FirebaseTooManyRequestsException

actual typealias FirebaseApiNotAvailableException = com.google.firebase.FirebaseApiNotAvailableException

actual fun initializeFirebaseApp(context: Any, options: FirebaseOptions) =
    FirebaseApp.initializeApp(context as Context, options)

actual typealias FirebaseApp = com.google.firebase.FirebaseApp

actual typealias FirebaseOptions = com.google.firebase.FirebaseOptions

actual typealias FirebaseOptionsBuilder = com.google.firebase.FirebaseOptions.Builder

actual fun getFirebaseApps(context: Any) = FirebaseApp.getApps(context as Context)