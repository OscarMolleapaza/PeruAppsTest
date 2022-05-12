
object Dependencies {

    object Libraries {
        // Google
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"

        // Koin
        const val koinCore = "org.koin:koin-android:${Versions.koin}"
        const val koinTest = "org.koin:koin-test:${Versions.koin}"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        // Retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        //Interceptor
        const val logginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
        //Gson
        const val gsonConverter =  "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        //Room
        const val roomDataBaseRun = "androidx.room:room-runtime:${Versions.room}"
        const val roomDataBaseCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomDataBaseCoroutines = "androidx.room:room-ktx:${Versions.room}"
        //Fresco facebook
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object KotlinLibraries {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesTest =  "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object AndroidLibraries {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
        const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
        const val exifInterface = "androidx.exifinterface:exifinterface:${Versions.exifInterface}"

        const val recycler = "androidx.recyclerview:recyclerview:${Versions.recycler}"

        const val recyclerDecorator = "it.xabaras.android:recyclerview-swipedecorator:${Versions.recyclerDecorator}"

        const val viewPager = "androidx.viewpager2:viewpager2:${Versions.viewPager}"
        const val pagedList ="androidx.paging:paging-runtime:${Versions.pagedList}"


    }

    object TestLibraries {
        // (Required) Writing and executing Unit Tests on the JUnit Platform
        const val JUNIT5_API = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5Version}"
        const val JUNIT5_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5Version}"
        const val EXT = "androidx.test.ext:junit:${Versions.EXT}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val ROOM = "androidx.room:room-testing:${Versions.room}"
        const val RULES = "androidx.test:rules:${Versions.TEST}"
        // Truth
        const val TRUTH = "com.google.truth:truth:${Versions.truthVersion}"
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINESTEST}"
        const val CORE = "androidx.test:core:${Versions.TEST}"
        const val ARCH_CORE = "androidx.arch.core:core-testing:${Versions.ARCH_CORE}"
        const val RUNNER = "androidx.test:runner:${Versions.TEST}"
        const val ROBOELECTRIC = "org.robolectric:robolectric:${Versions.ROBOELECTRIC}"
    }
}



