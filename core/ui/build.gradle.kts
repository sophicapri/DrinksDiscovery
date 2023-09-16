plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["targetSdkVersion"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_compiler_version"] as String
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    val composeVersion = rootProject.extra["compose_version"]
    val composeActivityVersion = rootProject.extra["compose_activity_version"]
    val lifecycleVersion = rootProject.extra["lifecycle_version"]
    val fragmentVersion = rootProject.extra["fragment_version"]
    val appCompatVersion = rootProject.extra["appcompat_version"]
    val navigationVersion = rootProject.extra["nav_version"]
    val coilVersion = rootProject.extra["coil_version"]

    api("androidx.fragment:fragment-ktx:$fragmentVersion")
    api("androidx.compose.ui:ui:$composeVersion")
    api("androidx.compose.material:material:$composeVersion")
    api("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    api("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
/*    api("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")   */
    api("androidx.activity:activity-compose:$composeActivityVersion")
    api("androidx.compose.ui:ui-tooling:$composeVersion")
    api("androidx.compose.material:material-icons-extended:$composeVersion")
    api("androidx.compose.foundation:foundation:$composeVersion")
    api("androidx.appcompat:appcompat:$appCompatVersion")
    api("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    api("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    api("io.coil-kt:coil-compose:$coilVersion")
}