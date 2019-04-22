group = "io.multiplatform"
version = "17.1.0-rev2"

buildscript {
    repositories {
        mavenLocal()
        jcenter()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.30")
    }
}

plugins {
    `maven-publish`
    kotlin("multiplatform") version "1.3.21"
}

repositories {
    mavenLocal()
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
}

kotlin {
    targets {
            //fromPreset(presets.android, 'android')
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

        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation("org.jetbrains.kotlin:kotlin-stdlib")
                    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.1.0")
                }
            }
            js().compilations["main"].defaultSourceSet  {
                dependencies {
                    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
                    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.0.0")
                }
            }
//        androidMain {
//            dependencies {
//                api 'com.google.firebase:firebase-firestore:18.2.0'
//                api 'com.google.firebase:firebase-auth:16.2.0'
//                api 'com.google.firebase:firebase-functions:16.3.0'
//                api 'com.google.firebase:firebase-database:16.1.0'
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0'
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.0'
//            }
//        }
            jvm().compilations["main"].defaultSourceSet {
                    kotlin.srcDir("src/androidMain/kotlin")
                    dependencies {
                        api("app.teamhub:firebase-java:0.1.0")
                        api("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.0")
                        implementation(kotlin("stdlib-jdk8"))
                        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0")
                    }
                }
        }
    }


//android {
//    compileSdkVersion 28
//    defaultConfig {
//        minSdkVersion 26
//        targetSdkVersion 28
//        versionCode 1
//        versionName "17.1.0-rev1"
//        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
//    }
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
//        }
//    }
//    sourceSets {
//        main {
//            manifest.srcFile "src/androidMain/AndroidManifest.xml"
//        }
//    }
//}

//publishing {
//    repositories {
//        maven {
//            url = uri(myMavenRepoWriteUrl)
//            credentials {
//                username  = myMavenRepoWriteUsername
//                password  = myMavenRepoWritePassword
//            }
//        }
//    }
//}



tasks {
    val copyPackageJson by registering(Copy::class) {
        from(file("package.json"))
        into(file("${buildDir}/node_module"))
    }
    
    val copyJS by registering(Copy::class) {
        from(file("${buildDir}/classes/kotlin/js/main/${project.name}.js"))
        into(file("${buildDir}/node_module"))   
        rename("${project.name}\\.js", "index.js")
    }
    
    val publishToNpm by registering(Exec::class) {
        dependsOn(copyPackageJson, copyJS)
        workingDir("${buildDir}/node_module")
        commandLine("npm publish")
    //args ['publish']
    }
}