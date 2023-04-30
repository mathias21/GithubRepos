plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = AppConfig.namespace
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = 1
        versionName = AppConfig.versionName

        testInstrumentationRunner = ProjectConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Versions.composeCompiler
    }

    buildFeatures {
        compose = true
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.appCompact)

    // Lifecycle
    implementation(Dependencies.androidLifecycleViewModelCompose)
    implementation(Dependencies.androidLifecycleViewmodelKtx)
    implementation(Dependencies.androidLifecycleRuntimeCompose)

    implementation(Dependencies.navigationUiKtx)

    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.accompanistSystemUiController)
    implementation(Dependencies.accompanistNavigationAnimation)
    implementation(Dependencies.composeAccompanistPermissions)

    debugImplementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeConstraintLayout)

    implementation(Dependencies.coilCompose)

    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    implementation(Dependencies.hiltNavigationCompose)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverterMoshi)
    implementation(Dependencies.retrofitKotlinxSerializationConverter)
    implementation(Dependencies.moshi)
    implementation(Dependencies.moshiKotlin)
    implementation(Dependencies.moshiAdapters)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.httpInterceptor)

    implementation(Dependencies.kotlinXSerialization)

    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomPaging) {
        exclude("org.jetbrains.kotlin")
    }

    implementation(Dependencies.timber)

    implementation(Dependencies.firebaseAnalytics)
    implementation(Dependencies.firebaseAnalyticsKtx)

    implementation(Dependencies.firebaseCrashlytics)
    implementation(Dependencies.firebaseCrashlyticsKtx)

    implementation(Dependencies.jodaTime)

    implementation(Dependencies.androidPagingCompose) {
        exclude("org.jetbrains.kotlin")
    }

    // Testing
    testImplementation(Dependencies.junit4)
    testImplementation(Dependencies.turbine)
    testImplementation(Dependencies.assertK)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.mockitoKotlin)

    // Instrumented tests
    androidTestImplementation(Dependencies.androidTestJunit)
    androidTestImplementation(Dependencies.espressoCore)

    androidTestImplementation(Dependencies.junit4)
    androidTestImplementation(Dependencies.composeJunit)
    debugImplementation(Dependencies.composeUiTestManifest)
    // For instrumented tests.
    androidTestImplementation(Dependencies.hiltAndroidTesting)
    // ...with Kotlin.
    kaptAndroidTest(Dependencies.hiltAndroidCompiler)

    androidTestImplementation(Dependencies.mockitoAndroid)
    androidTestImplementation(Dependencies.mockitoKotlin)
}