buildscript {
    extra["androidx_core_version"] = "1.8.0"
    extra["espresso_version"] = "3.4.0"
    extra["compose_version"] = "1.1.1"
    extra["compose_compiler_version"] = "1.2.0"
    extra["lifecycle_version"] = "2.6.0-alpha01"
    extra["compose_activity_version"] = "1.5.0"
    extra["nav_version"] = "2.5.0"
    extra["fragment_version"] = "1.5.0"
    extra["appcompat_version"] = "1.4.2"
    extra["hilt_version"] = "2.43"
    extra["coroutines_version"] = "1.6.4"
    extra["retrofit_version"] = "2.9.0"
    extra["okhttp_version"] = "5.0.0-alpha.10"
    extra["moshi_version"] = "1.13.0"
    extra["mockk_version"] = "1.12.4"
    extra["paging_version"] = "1.0.0-alpha15"
    extra["coil_version"] = "2.1.0"
    extra["junit_version"] = "4.13.2"
    extra["junit_android_version"] = "1.1.3"
    extra["mock_web_server_version"] = "4.10.0"
    extra["core_testing_version"] = "2.1.0"

    allprojects {
        extra["minSdkVersion"] = 26
        extra["compileSdkVersion"] = 32
        extra["targetSdkVersion"] = 32
    }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra["hilt_version"]}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${rootProject.extra["nav_version"]}")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.0" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}