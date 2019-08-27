import org.apache.tools.ant.taskdefs.condition.Os

group = "io.multiplatform"
version = "17.1.0-rev2"

plugins {
    `maven-publish`
    id("com.android.library") version "3.4.0"
    kotlin("multiplatform") version "1.3.40"
}

repositories {
    mavenLocal()
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
}

kotlin {
    android()

    jvm {
        val main by compilations.getting {
            kotlinOptions {
                jvmTarget ="1.8"
            }
        }
    }

    js {
        val main by compilations.getting {
            kotlinOptions {
                sourceMap = true
                sourceMapEmbedSources = "always"
                moduleKind = "commonjs"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.2.2")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.2.2")
            }
        }
        val androidMain by getting {
            dependencies {
                api("com.google.firebase:firebase-firestore:19.0.2")
                api("com.google.firebase:firebase-auth:17.0.0")
                api("com.google.firebase:firebase-functions:17.0.0")
                api("com.google.firebase:firebase-database:17.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.2.2")
            }
        }
        val jvmMain by getting {
            kotlin.srcDir("src/androidMain/kotlin")
            dependencies {
                api("app.teamhub:firebase-java:0.1.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.2.2")
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.2")
            }
        }
    }
}


android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(26)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "17.1.0-rev1"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }
}

tasks {
    val copyPackageJson by registering(Copy::class) {
        from(file("package.json"))
        into(file("$buildDir/node_module"))
    }

    val copyJS by registering(Copy::class) {
        from(file("$buildDir/classes/kotlin/js/main/${project.name}.js"))
        into(file("$buildDir/node_module"))
        rename("${project.name}\\.js", "index.js")
    }
    
    val copySourceMap by registering(Copy::class) {
        from(file("$buildDir/classes/kotlin/js/main/${project.name}.js.map"))
        into(file("$buildDir/node_module"))
        rename("${project.name}\\.js.map", "index.js.map")
    }
    
    val publishToNpm by registering(Exec::class) {
        dependsOn(copyPackageJson, copyJS, copySourceMap)
        workingDir("$buildDir/node_module")
        if(Os.isFamily(Os.FAMILY_WINDOWS)) {
            commandLine("cmd", "/c", "npm publish --registry http://localhost:4873 & npm publish --registry https://npm.pkg.github.com/")
        } else {
            commandLine("npm", "publish", "--registry http://localhost:4873")
            commandLine("npm", "publish", "--registry https://npm.pkg.github.com/")
        }
    }
}