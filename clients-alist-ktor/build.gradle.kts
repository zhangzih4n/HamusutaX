import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(jetbrains.plugins.kotlin.multiplatform)
    alias(jetbrains.plugins.kotlin.plugin.serialization)
    id("maven-publish")
}

kotlin {
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    js {
        browser()
        nodejs()
    }
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
    watchosSimulatorArm64()
    watchosX64()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("cio") {
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
            }
        }
    }

    sourceSets {
        val cioMain by getting

        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.ktor)
            implementation(jetbrains.kotlinx.atomicfu)
            implementation(jetbrains.kotlinx.coroutines.core)
            implementation(jetbrains.kotlinx.collections.immutable)
            implementation(jetbrains.kotlinx.datetime)
            implementation(jetbrains.kotlinx.io.core)
            implementation(jetbrains.kotlinx.io.bytestring)
            implementation(jetbrains.kotlinx.serialization.json)
            implementation(ktor.client.core)
            implementation(ktor.client.encoding)
            implementation(ktor.client.content.negotiation)
            implementation(ktor.serialization.kotlinx.json)
        }
        commonTest.dependencies {
            implementation(jetbrains.kotlinx.coroutines.test)
        }
        jsMain.dependencies {
            implementation(ktor.client.engine.js)
        }
        cioMain.dependencies {
            implementation(ktor.client.engine.cio)
        }
        all {
            languageSettings.apply {
                optIn("kotlin.ExperimentalStdlibApi")
                optIn("kotlin.ExperimentalUnsignedTypes")
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.io.encoding.ExperimentalEncodingApi")
                optIn("kotlin.js.ExperimentalJsCollectionsApi")
                optIn("kotlinx.datetime.format.FormatStringsInDatetimeFormats")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }
    }
}
