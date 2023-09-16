plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    val okHttpVersion = rootProject.extra["okhttp_version"]
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")

    // Moshi
    val moshiVersion = rootProject.extra["moshi_version"]
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")

    // TESTS
    // JUnit
    val jUnitVersion = rootProject.extra["junit_version"]
    testImplementation("junit:junit:$jUnitVersion")

    // MockK
    val mockkVersion = rootProject.extra["mockk_version"]
    testImplementation("io.mockk:mockk:$mockkVersion")
}