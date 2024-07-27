import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.android.library)
    id("maven-publish")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
        publishLibraryVariants("release", "debug")
    }
    js {
        browser()
        nodejs()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
    }
    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            api(projects.core)
            implementation(compose.runtime)
            implementation(compose.animation)
            implementation(compose.animationGraphics)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.compose.navigation)
            implementation(libs.coil.compose)
            implementation(libs.kotlin.reflect)
            implementation(kotlinx.coroutines.core)
            implementation(kotlinx.serialization.json)
        }
        androidMain.dependencies {
            implementation(androidx.core.ktx)
            implementation(androidx.webkit)
            implementation(kotlinx.coroutines.android)
        }
        commonTest.dependencies {
            implementation(kotlinx.coroutines.test)
        }
        all {
            languageSettings.apply {
                optIn("androidx.compose.foundation.ExperimentalFoundationApi")
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("androidx.compose.ui.ExperimentalComposeUiApi")
            }
        }
    }
}

android {
    namespace = "hamusutax.compose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        resources.excludes += "DebugProbesKt.bin"
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
