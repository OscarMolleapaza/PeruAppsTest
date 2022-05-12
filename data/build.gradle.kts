plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("org.jetbrains.kotlin.kapt")
}

android {
    compileSdkVersion (30)

    defaultConfig {
        minSdkVersion (Versions.minSdk)
        targetSdkVersion (Versions.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles ("consumer-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BaseURL", "\"https://hn.algolia.com/api/v1/\"")
        }
        create("qa"){
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BaseURL", "\"https://hn.algolia.com/api/v1/\"")
        }
        getByName("debug"){
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BaseURL", "\"https://hn.algolia.com/api/v1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources.excludes.add("META-INF/atomicfu.kotlin_module")
        resources.excludes.add("META-INF/*")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation (fileTree("libs") {include(listOf("*.jar"))})
    implementation (Dependencies.KotlinLibraries.kotlin)
    implementation (project(ConfigGradle.Module.domain))
    implementation (Dependencies.Libraries.koinCore)
    implementation (Dependencies.Libraries.retrofit)
    implementation (Dependencies.Libraries.logginInterceptor)
    implementation (Dependencies.Libraries.gsonConverter)
    implementation (Dependencies.Libraries.roomDataBaseRun){
        exclude(group = "org.xerial")
    }
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.3.1")

    kapt (Dependencies.Libraries.roomDataBaseCompiler){
        exclude(group = "org.xerial")
    }
    kapt ("org.xerial:sqlite-jdbc:3.34.0")
    annotationProcessor (Dependencies.Libraries.roomDataBaseCompiler)
    implementation (Dependencies.Libraries.roomDataBaseCoroutines)
    implementation (Dependencies.KotlinLibraries.coroutinesCore)
    implementation (Dependencies.KotlinLibraries.coroutinesAndroid)
    testImplementation (Dependencies.KotlinLibraries.coroutinesTest)
    testImplementation (Dependencies.Libraries.koinTest)

    testImplementation(Dependencies.TestLibraries.MOCKK)
    testImplementation(Dependencies.TestLibraries.ROOM)
    testImplementation(Dependencies.TestLibraries.COROUTINES_TEST)
    testImplementation(Dependencies.TestLibraries.JUNIT5_API)
    testRuntimeOnly(Dependencies.TestLibraries.JUNIT5_ENGINE)
    testImplementation(Dependencies.TestLibraries.RULES)
    testImplementation(Dependencies.TestLibraries.TRUTH)
    testImplementation(Dependencies.TestLibraries.ROBOELECTRIC)
    testImplementation(Dependencies.TestLibraries.ARCH_CORE)
    testImplementation(Dependencies.TestLibraries.CORE)
}