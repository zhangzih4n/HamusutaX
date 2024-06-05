import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinPluginSerialization)
    alias(libs.plugins.androidLibrary)
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

    jvm()

    js {
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
    }

    /*@OptIn(ExperimentalWasmDsl::class)
    wasmWasi()*/

    mingwX64()
    linuxX64()
    linuxArm64()
    macosX64()
    macosArm64()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()
    watchosSimulatorArm64()
    watchosDeviceArm64()
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("jvmAndAndroid") {
                withJvm()
                withAndroidTarget()
            }

            group("nonJvm") {
                group("jsAndWasmJs") {
                    withJs()
                    withWasmJs()
                }

                group("native") {
                    group("unix") {
                        group("apple")
                        group("linux")
                    }
                }
            }
        }
    }

    sourceSets {
        val jvmAndAndroidMain by getting
        val nonJvmMain by getting
        val jsAndWasmJsMain by getting
        val wasmJsMain by getting

        commonMain.dependencies {
            implementation(libs.kotlinx.atomicfu)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.collections.immutable)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.io.core)
            implementation(libs.kotlinx.io.bytestring)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.okio)
        }
        commonTest.dependencies {
            implementation(libs.kotlinx.coroutines.test)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.kotlinx.coroutines.android)
        }
        jvmAndAndroidMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.encoding)
            implementation(libs.ktor.client.websockets)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.xmlutil)
            implementation(libs.okhttp)
            implementation(libs.jsoup)
        }
        jvmMain.dependencies {}
        jsMain.dependencies {}
        nativeMain.dependencies {}
        appleMain.dependencies {}
        iosMain.dependencies {}
        macosMain.dependencies {}
        tvosMain.dependencies {}
        watchosMain.dependencies {}
        linuxMain.dependencies {}
        mingwMain.dependencies {}

        all {
            languageSettings.apply {
                optIn("kotlin.ExperimentalStdlibApi")
                optIn("kotlin.ExperimentalUnsignedTypes")
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.io.encoding.ExperimentalEncodingApi")
                optIn("kotlinx.datetime.format.FormatStringsInDatetimeFormats")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }
    }
}

android {
    namespace = "hamusutax.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        // See: https://github.com/Kotlin/kotlinx.coroutines#avoiding-including-the-debug-infrastructure-in-the-resulting-apk
        resources.excludes += "DebugProbesKt.bin"
    }
}
