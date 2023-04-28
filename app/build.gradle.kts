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

    // This version (1.0.5) of the Compose Compiler requires Kotlin version 1.5.31
    // but you appear to be using Kotlin version 1.7.20 which is not known to be compatible.
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

    implementation(Dependencies.kotlinKtx)
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

    implementation(Dependencies.timber)

    implementation(Dependencies.firebaseAnalytics)
    implementation(Dependencies.firebaseAnalyticsKtx)

    implementation(Dependencies.firebaseCrashlytics)
    implementation(Dependencies.firebaseCrashlyticsKtx)

    implementation(Dependencies.jodaTime)

    // Testing
    testImplementation(Dependencies.junit4)

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