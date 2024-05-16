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
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
        //publishLibraryVariants("release", "debug")
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
    wasmWasi {
        nodejs()
    }*/

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

    sourceSets {
        val mingwX64Main by getting
        val wasmJsMain by getting

        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.collections.immutable)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.io)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.okio)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.okhttp)
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
}
