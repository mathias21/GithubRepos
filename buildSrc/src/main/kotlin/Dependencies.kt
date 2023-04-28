import Dependencies.Versions.androidTestJunitVersion
import Dependencies.Versions.appCompactVersion
import Dependencies.Versions.coilComposeVersion
import Dependencies.Versions.composeAccompanistNavigationAnimationVersion
import Dependencies.Versions.composeAccompanistVersion
import Dependencies.Versions.composeActivityVersion
import Dependencies.Versions.composeConstraintLayoutVersion
import Dependencies.Versions.composeVersion
import Dependencies.Versions.espressoVersion
import Dependencies.Versions.hiltNavigationComposeVersion
import Dependencies.Versions.hiltVersion
import Dependencies.Versions.httpInterceptorVersion
import Dependencies.Versions.hummingbirdVersion
import Dependencies.Versions.junit4Version
import Dependencies.Versions.kotlinKtxVersion
import Dependencies.Versions.kotlinVersion
import Dependencies.Versions.kotlinXSerializationVersion
import Dependencies.Versions.lifecycleVersion
import Dependencies.Versions.materialVersion
import Dependencies.Versions.mockitoCoreVersion
import Dependencies.Versions.mockitoKotlinVersion
import Dependencies.Versions.moshiVersion
import Dependencies.Versions.navigationVersion
import Dependencies.Versions.okhttpVersion
import Dependencies.Versions.retrofitKotlinxSerializationVersion
import Dependencies.Versions.retrofitVersion
import Dependencies.Versions.roomVersion
import Dependencies.Versions.securityCryptoVersion
import Dependencies.Versions.timberVersion

object Dependencies {

    object Versions {
        val gradleVersion = "7.4.2"
        val kotlinVersion = "1.7.20"

        val kotlinKtxVersion = "1.7.0"


        // Android
        val appCompactVersion = "1.5.1"
        val lifecycleVersion = "2.6.0-alpha01"
        val securityCryptoVersion = "1.1.0-alpha03"


        // Hilt
        val hiltVersion = "2.44.2"
        val hiltNavigationComposeVersion = "1.0.0"

        val timberVersion = "4.7.1"

        // Compose
        val composeVersion = "1.3.1"
        val composeCompiler = "1.3.2"
        val composeActivityVersion = "1.6.1"
        val composeConstraintLayoutVersion = "1.0.1"
        val composeAccompanistVersion = "0.27.1"
        val composeAccompanistNavigationAnimationVersion = "0.27.1"

        val coilComposeVersion = "2.2.2"

        // Material
        val materialVersion = "1.0.1"

        // Navigation
        val navigationVersion = "2.5.1"

        // Blockchain
        val hummingbirdVersion = "1.6.6"

        // Network
        val moshiVersion = "1.11.0"
        val retrofitVersion = "2.9.0"
        val okhttpVersion = "4.8.1"
        val httpInterceptorVersion = "4.8.1"
        val retrofitKotlinxSerializationVersion = "0.8.0"

        val kotlinXSerializationVersion = "1.4.0"

        val roomVersion = "2.4.3"
        val dateTimeVersion = "0.4.0"
        val mpChartsVersion = "v3.1.0"

        val jodaTimeVersion = "2.12.2"

        val konfettiVersion = "2.0.2"

        val googleAdsVersion = "22.0.0"

        val googleServicesVersion = "4.3.15"
        val firebaseAnalyticsVersion = "21.2.1"
        val firebaseCrashlyticsVersion = "18.3.6"
        val crashlyticsGradleVersion = "2.9.4"

        // TESTING

        val mockitoCoreVersion = "3.12.4"
        val mockitoKotlinVersion = "2.2.0"
        val junit4Version = "4.13.2"
        val androidTestJunitVersion = "1.1.4"
        val espressoVersion = "3.5.0"

        // TOOLS

        val relayVersion = "0.3.02"
    }


    // Dependencies

    // ANDROID

    val kotlinKtx = "androidx.core:core-ktx:$kotlinKtxVersion"
    val appCompact = "androidx.appcompat:appcompat:$appCompactVersion"
    val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android$kotlinVersion"

    // Lifecycle
    val androidLifecycleViewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    val androidLifecycleViewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
    val androidLifecycleRuntimeCompose =
        "androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion"
    val androidLifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"


    // HILT
    val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:$hiltVersion"
    val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    // COMPOSE
    val composeUi = "androidx.compose.ui:ui:$composeVersion"
    val activityCompose = "androidx.activity:activity-compose:$composeActivityVersion"
    val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:$composeConstraintLayoutVersion"
    val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion"
    val composeIconsCore = "androidx.compose.material:material-icons-core:$composeVersion"
    val composeUiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    val accompanistSystemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:$composeAccompanistVersion"
    val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:$composeAccompanistNavigationAnimationVersion"
    val composeAccompanistInsets = "com.google.accompanist:accompanist-insets:$composeAccompanistVersion"
    val composeAccompanistPermissions = "com.google.accompanist:accompanist-permissions:$composeAccompanistVersion"

    val composeIconsExt = "androidx.compose.material:material-icons-extended:$composeVersion"
    val composeMaterial = "androidx.compose.material:material:$composeVersion"
    val composeMaterial3 = "androidx.compose.material3:material3:$materialVersion"

    // Coil
    val coilCompose = "io.coil-kt:coil-compose:$coilComposeVersion"

    // Navigation
    val composeNavigation = "androidx.navigation:navigation-compose:$navigationVersion"
    val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    // Blockchain
    val hummingbird = "com.sparrowwallet:hummingbird:$hummingbirdVersion"

    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    val retrofitKotlinxSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$retrofitKotlinxSerializationVersion"
    val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    val moshiAdapters = "com.squareup.moshi:moshi-adapters:$moshiVersion"
    val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    val httpInterceptor = "com.squareup.okhttp3:logging-interceptor:$httpInterceptorVersion"

    val kotlinXSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinXSerializationVersion"

    // ROOM
    val room = "androidx.room:room-runtime:$roomVersion"
    val roomKtx = "androidx.room:room-ktx:$roomVersion"
    val roomCompiler = "androidx.room:room-compiler:$roomVersion"

    val timber = "com.jakewharton.timber:timber:$timberVersion"

    val jodaTime = "joda-time:joda-time:${Versions.jodaTimeVersion}"
    val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTimeVersion}"

    val mpCharts = "com.github.PhilJay:MPAndroidChart:${Versions.mpChartsVersion}"

    val konfetti = "nl.dionsegijn:konfetti-compose:${Versions.konfettiVersion}"


    val ads = "com.google.android.gms:play-services-ads:${Versions.googleAdsVersion}"
    val googleServices = "com.google.gms:google-services:${Versions.googleServicesVersion}"
    val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalyticsVersion}"
    val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx:${Versions.firebaseAnalyticsVersion}"

    val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:${Versions.firebaseCrashlyticsVersion}"
    val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebaseCrashlyticsVersion}"
    val crashlyticsGradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsGradleVersion}"

    // TESTING
    // Test rules and transitive dependencies:
    val composeJunit = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    // Needed for createAndroidComposeRule, but not createComposeRule:
    val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"
    val androidTestJunit = "androidx.test.ext:junit:$androidTestJunitVersion"
    val junit4 = "junit:junit:$junit4Version"
    val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"

    // Mockito
    val mockitoAndroid = "org.mockito:mockito-android:$mockitoCoreVersion"
    val mockitoCore = "org.mockito:mockito-core:$mockitoCoreVersion"
    val mockitoInline = "org.mockito:mockito-inline:$mockitoCoreVersion"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion"
}