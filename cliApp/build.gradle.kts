import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.plugin.allopen)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
    alias(kotlinx.plugins.benchmark)
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
            implementation(projects.cli)
            implementation(projects.formatsBencode)
            implementation(projects.formatsBittorrent)
            implementation(projects.formatsEbml)
            implementation(kotlinx.atomicfu)
            implementation(kotlinx.benchmark.runtime)
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
        jsMain.dependencies {
            implementation(projects.okhttp)
            implementation(ktor.client.js)
        }
        javaMain.dependencies {
            implementation(projects.okhttp)
            implementation(ktor.client.okhttp)
            implementation(libs.okhttp)
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
            implementation(ktor.client.cio)
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
