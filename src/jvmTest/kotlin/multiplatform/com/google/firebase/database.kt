package multiplatform.com.google.firebase

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.FirebasePlatform
import com.google.firebase.database.*
import org.junit.Test
import com.google.firebase.firestore.util.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import multiplatform.com.google.firebase.database.*
import multiplatform.com.google.firebase.database.DataSnapshot
import multiplatform.com.google.firebase.database.DatabaseError
import multiplatform.com.google.firebase.database.ValueEventListener
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLSession



public class DatabaseTest {

    //@Test
    fun test() = runBlocking {
        Logger.setLogLevel(Logger.Level.DEBUG)
        val options = FirebaseOptions.Builder()
                .setApplicationId("app.teamhub.core")
                .setApiKey("AIzaSyCv8kZWZTaG0ONkPIonaA7by-BXTlsLBNo")
                .setDatabaseUrl("https://teamhub-app.firebaseio.com")
                .setStorageBucket("teamhub-app.appspot.com")
                .setProjectId("teamhub-app")
                .build()

        FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {
            override fun store(key: String, value: String) = Unit
            override fun retrieve(key: String): String? = null
            override fun clear(key: String) = Unit
            override fun log(msg: String) = println(msg)
        })
        initializeFirebaseApp(Application(), options)
        // since I can connect from multiple devices, we store each connection instance separately
        // any time that connectionsRef's value is null (i.e. has no children) I am offline
        val database = getFirebaseDatabase()
        database.setLogLevel(LoggerLevel.DEBUG)
        database.setPersistenceEnabled(false)
        val myConnectionsRef = database.getReference("documents/nbko0zjXyJl8YWewMAHIMBFS/file")
        myConnectionsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                println("changed")
            }

            override fun onCancelled(error: DatabaseError) {
                throw Exception(error.toString())
            }
        })
        delay(100000)
    }
}