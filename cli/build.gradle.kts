import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(jetbrains.plugins.kotlin.multiplatform)
    alias(jetbrains.plugins.kotlin.plugin.serialization)
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
            implementation(jetbrains.kotlinx.atomicfu)
            implementation(jetbrains.kotlinx.coroutines.core)
            implementation(jetbrains.kotlinx.collections.immutable)
            implementation(jetbrains.kotlinx.datetime)
            implementation(jetbrains.kotlinx.io.core)
            implementation(jetbrains.kotlinx.io.bytestring)
            implementation(jetbrains.kotlinx.serialization.json)
            implementation(jetbrains.kotlinx.serialization.json.io)
            implementation(squareup.okio)
        }
        commonTest.dependencies {
            implementation(jetbrains.kotlinx.coroutines.test)
        }
        androidMain.dependencies {
            implementation(ktor.client.engine.android)
        }
        jsMain.dependencies {
            implementation(projects.okhttp)
            implementation(ktor.client.engine.js)
        }
        jvmMain.dependencies {
            implementation(ktor.client.engine.apache)
            implementation(ktor.client.engine.apache5)
            implementation(ktor.client.engine.java)
            implementation(ktor.client.engine.jetty)
        }
        mingwMain.dependencies {
            implementation(ktor.client.engine.winhttp)
        }
        javaMain.dependencies {
            implementation(projects.okhttp)
            implementation(libs.jna.platform)
            implementation(ktor.client.engine.okhttp)
            implementation(squareup.okhttp)
        }
        appleExcludeWatchosDeviceMain.dependencies {
            implementation(ktor.client.engine.darwin)
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
            implementation(ktor.client.engine.cio)
        }
        curlMain.dependencies {
            implementation(ktor.client.engine.curl)
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
