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
    watchosDeviceArm64()
    watchosSimulatorArm64()
    watchosX64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.formatsBencode)
            implementation(jetbrains.kotlinx.io.core)
            implementation(jetbrains.kotlinx.io.bytestring)
            implementation(jetbrains.kotlinx.serialization.core)
            implementation(kotlincrypto.hash.sha1)
            implementation(kotlincrypto.hash.sha2)
        }
        all {
            languageSettings.apply {
                optIn("kotlin.ExperimentalStdlibApi")
                optIn("kotlin.ExperimentalUnsignedTypes")
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.io.encoding.ExperimentalEncodingApi")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }
    }
}
