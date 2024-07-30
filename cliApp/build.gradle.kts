import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(jetbrains.plugins.kotlin.multiplatform)
    alias(jetbrains.plugins.kotlin.plugin.allopen)
    alias(jetbrains.plugins.kotlin.plugin.serialization)
    alias(jetbrains.plugins.kotlinx.benchmark)
    alias(google.plugins.ksp)
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = "cliApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        nodejs()
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "cliApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        nodejs()
        binaries.executable()
    }
    jvm {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        mainRun {
          mainClass.set("MainKt")
        }
    }
    mingwX64 {
        binaries.executable()
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
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
        }
    }

    sourceSets {
        val javaMain by getting
        val ktorMain by getting
        val ktorCioMain by getting

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
            implementation(projects.crypto)
            implementation(projects.cli)
            implementation(projects.formatsBencode)
            implementation(projects.formatsBittorrent)
            implementation(projects.formatsEbml)
            implementation(jetbrains.kotlinx.atomicfu)
            implementation(jetbrains.kotlinx.benchmark.runtime)
            implementation(jetbrains.kotlinx.coroutines.core)
            implementation(jetbrains.kotlinx.collections.immutable)
            implementation(jetbrains.kotlinx.datetime)
            implementation(jetbrains.kotlinx.io.core)
            implementation(jetbrains.kotlinx.io.bytestring)
            implementation(jetbrains.kotlinx.serialization.json)
            implementation(jetbrains.kotlinx.serialization.json.io)
            implementation(kotlincrypto.hash.md)
            implementation(kotlincrypto.hash.sha1)
            implementation(kotlincrypto.hash.sha2)
            implementation(kotlincrypto.hash.sha3)
            implementation(squareup.okio)
        }
        commonTest.dependencies {
            implementation(jetbrains.kotlinx.coroutines.test)
        }
        jsMain.dependencies {
            implementation(projects.okhttp)
            implementation(ktor.client.engine.js)
        }
        javaMain.dependencies {
            implementation(projects.okhttp)
            implementation(ktor.client.engine.okhttp)
            implementation(squareup.okhttp)
            implementation(libs.jna.platform)
            implementation(libs.kaml)
            implementation(libs.ksoup)
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
    }
}

benchmark {
    targets {
        register("jvm")
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}
