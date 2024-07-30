import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(jetbrains.plugins.kotlin.multiplatform)
    alias(jetbrains.plugins.kotlin.plugin.compose)
    alias(jetbrains.plugins.kotlin.plugin.serialization)
    alias(jetbrains.plugins.compose)
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
            implementation(jetbrains.compose.navigation)
            implementation(libs.coil.compose)
            implementation(jetbrains.kotlin.reflect)
            implementation(jetbrains.kotlinx.coroutines.core)
            implementation(jetbrains.kotlinx.serialization.json)
        }
        androidMain.dependencies {
            implementation(androidx.core.ktx)
            implementation(androidx.webkit)
            implementation(jetbrains.kotlinx.coroutines.android)
        }
        commonTest.dependencies {
            implementation(jetbrains.kotlinx.coroutines.test)
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
