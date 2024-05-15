plugins {
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinPluginSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    id("maven-publish")
}

android {
    namespace = "hamusutax.android"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(compose.ui)
    implementation(compose.uiTooling)
    implementation(compose.animation)
    implementation(compose.animationGraphics)
    implementation(compose.material3)
    api(projects.core)
    api(projects.jvm)
    api(projects.compose)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    implementation(libs.coil.compose)
}

publishing {
    publications.register<MavenPublication>("release") {
        artifactId = "android-compose"
        afterEvaluate {
            from(components["release"])
        }
    }
}
