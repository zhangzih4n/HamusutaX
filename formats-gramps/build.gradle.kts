import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(jetbrains.plugins.kotlin.multiplatform)
    alias(jetbrains.plugins.kotlin.plugin.serialization)
    id("maven-publish")
}

kotlin {
//    androidNativeArm32()
//    androidNativeArm64()
//    androidNativeX86()
//    androidNativeX64()
    iosArm64()
    iosSimulatorArm64()
    iosX64()
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
    jvm("desktop")
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
            group("jvm") {
                withAndroidTarget()
                withJvm()
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(jetbrains.kotlinx.atomicfu)
            implementation(jetbrains.kotlinx.coroutines.core)
            implementation(jetbrains.kotlinx.collections.immutable)
            implementation(jetbrains.kotlinx.io.core)
            implementation(jetbrains.kotlinx.io.bytestring)
            implementation(jetbrains.kotlinx.serialization.json)
            implementation(libs.xmlutil)
        }
        commonTest.dependencies {
            implementation(jetbrains.kotlinx.coroutines.test)
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
