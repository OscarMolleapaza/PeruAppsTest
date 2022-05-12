plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.secrets_gradle_plugin") version "0.5"
}

android {
    compileSdk= Versions.compileSdk
    buildToolsVersion =Versions.buildToolsVersion
    defaultConfig {
        applicationId = "com.omolleapaza.peruapps.peruappstechnicaltest"
        minSdk= Versions.minSdk
        targetSdk =Versions.targetSdk
        versionCode = ConfigGradle.Releases.versionCode
        versionName = ConfigGradle.Releases.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("qa") {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("debug") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }


    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar")) })
    implementation(Dependencies.KotlinLibraries.kotlin)
    implementation(Dependencies.AndroidLibraries.appCompat)
    implementation(Dependencies.AndroidLibraries.coreKtx)
    implementation(Dependencies.AndroidLibraries.cardView)
    implementation(Dependencies.Libraries.koinCore)
    implementation(Dependencies.Libraries.koinViewModel)
    implementation(Dependencies.Libraries.materialDesign)
    implementation(Dependencies.Libraries.gson)
    implementation(Dependencies.Libraries.glide)
    kapt(Dependencies.Libraries.glideCompiler)
    implementation(Dependencies.AndroidLibraries.constraintlayout)
    implementation(Dependencies.AndroidLibraries.legacySupport)
    implementation(Dependencies.AndroidLibraries.navigationFragment)
    implementation(Dependencies.AndroidLibraries.navigationUi)
    implementation(Dependencies.AndroidLibraries.fragment)
    implementation(Dependencies.AndroidLibraries.pagedList)
    implementation(Dependencies.AndroidLibraries.recycler)
    implementation(Dependencies.AndroidLibraries.recyclerDecorator)
    implementation(Dependencies.AndroidLibraries.activity)
    implementation(Dependencies.AndroidLibraries.exifInterface)
    implementation(Dependencies.AndroidLibraries.viewPager)
    implementation(project(ConfigGradle.Module.domain))
    implementation(project(ConfigGradle.Module.data))
    testImplementation(Dependencies.Libraries.koinTest)


}