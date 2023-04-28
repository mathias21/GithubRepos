// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.library") version Dependencies.Versions.gradleVersion apply false
    id("org.jetbrains.kotlin.android") version Dependencies.Versions.kotlinVersion apply false
    id("com.google.dagger.hilt.android") version Dependencies.Versions.hiltVersion apply false
    id("org.jetbrains.kotlin.jvm") version Dependencies.Versions.kotlinVersion apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(Dependencies.googleServices)
        classpath(Dependencies.crashlyticsGradlePlugin)
    }
}