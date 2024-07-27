import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
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
            implementation(kotlinx.atomicfu)
            implementation(kotlinx.coroutines.core)
            implementation(kotlinx.collections.immutable)
            implementation(kotlinx.io.core)
            implementation(kotlinx.io.bytestring)
            implementation(kotlinx.serialization.json)
            implementation(libs.xmlutil)
        }
        commonTest.dependencies {
            implementation(kotlinx.coroutines.test)
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
