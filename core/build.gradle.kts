import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.android.library)
    id("maven-publish")
}

kotlin {
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()
    iosArm64()
    iosSimulatorArm64()
    iosX64()
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
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmWasi()
    jvm()
    linuxArm64()
    linuxX64()
    macosArm64()
    macosX64()
    mingwX64()
    tvosArm64()
    tvosSimulatorArm64()
    tvosX64()
    watchosArm32()
    watchosArm64()
    watchosDeviceArm64()
    watchosSimulatorArm64()
    watchosX64()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("java") {
                withAndroidTarget()
                withJvm()
            }
            group("nonJava") {
                group("native") {
                    group("apple") {
                        group("watchosExcludeDevice") {
                            withWatchosArm32()
                            withWatchosArm64()
                            withWatchosSimulatorArm64()
                            withWatchosX64()
                        }
                    }
                    group("appleExcludeWatchosDevice") {
                        group("ios")
                        group("macos")
                        group("tvos")
                        group("watchosExcludeDevice")
                    }
                }
                withJs()
                withWasmJs()
            }
        }
    }

    sourceSets {
        val nonJavaMain by getting

        commonMain.dependencies {
            implementation(kotlinx.datetime)
            implementation(kotlinx.io.core)
            implementation(kotlinx.io.bytestring)
            implementation(kotlinx.serialization.core)
        }
        androidMain.dependencies {
            implementation(androidx.core.ktx)
        }
        all {
            languageSettings.apply {
                optIn("kotlin.ExperimentalStdlibApi")
                optIn("kotlin.ExperimentalUnsignedTypes")
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.io.encoding.ExperimentalEncodingApi")
                optIn("kotlin.js.ExperimentalJsCollectionsApi")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
                optIn("kotlinx.datetime.format.FormatStringsInDatetimeFormats")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }
    }
}

android {
    namespace = "hamusutax"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        // https://github.com/Kotlin/kotlinx.coroutines#avoiding-including-the-debug-infrastructure-in-the-resulting-apk
        resources.excludes += "DebugProbesKt.bin"
    }
}
