import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
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
    jvm()
    linuxArm64()
    linuxX64()
    macosArm64()
    macosX64()
    mingwX64()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
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
            group("ktor") {
                group("ktorCio") {
                    group("java") {
                        withAndroidTarget()
                        withJvm()
                    }
                    withIos()
                    withMacos()
                    withTvos()
                    withWatchosArm32()
                    withWatchosArm64()
                    withWatchosSimulatorArm64()
                    withWatchosX64()
                    withLinux()
                }
                withJs()
                withMingw()
            }
            group("curl") {
                withLinuxX64()
                withMacos()
                withMingw()
            }
        }
    }

    sourceSets {
        val javaMain by getting
        val appleExcludeWatchosDeviceMain by getting
        val ktorMain by getting
        val ktorCioMain by getting
        val curlMain by getting

        all {
            languageSettings.apply {
                optIn("kotlin.ExperimentalStdlibApi")
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.formatsBencode)
            implementation(projects.formatsEbml)
            implementation(kotlinx.atomicfu)
            implementation(kotlinx.coroutines.core)
            implementation(kotlinx.collections.immutable)
            implementation(kotlinx.datetime)
            implementation(kotlinx.io.core)
            implementation(kotlinx.io.bytestring)
            implementation(kotlinx.serialization.json)
            implementation(kotlinx.serialization.json.io)
            implementation(libs.okio)
        }
        commonTest.dependencies {
            implementation(kotlinx.coroutines.test)
        }
        androidMain.dependencies {
            implementation(ktor.client.android)
        }
        jsMain.dependencies {
            implementation(projects.okhttp)
            implementation(ktor.client.js)
        }
        jvmMain.dependencies {
            implementation(ktor.client.apache)
            implementation(ktor.client.apache5)
            implementation(ktor.client.java)
            implementation(ktor.client.jetty)
        }
        mingwMain.dependencies {
            implementation(ktor.client.winhttp)
        }
        javaMain.dependencies {
            implementation(projects.okhttp)
            implementation(libs.jna.platform)
            implementation(ktor.client.okhttp)
            implementation(libs.okhttp)
        }
        appleExcludeWatchosDeviceMain.dependencies {
            implementation(ktor.client.darwin)
        }
        ktorMain.dependencies {
            implementation(projects.ktor)
            implementation(projects.clientsAlistKtor)
            implementation(ktor.client.core)
            implementation(ktor.client.auth)
            implementation(ktor.client.encoding)
            implementation(ktor.client.websockets)
            implementation(ktor.client.logging)
            implementation(ktor.client.content.negotiation)
            implementation(ktor.serialization.kotlinx.json)
        }
        ktorCioMain.dependencies {
            implementation(ktor.client.cio)
        }
        curlMain.dependencies {
            implementation(ktor.client.curl)
        }
    }
}

android {
    namespace = "hamusutax.cli"
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
}
