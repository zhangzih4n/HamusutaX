import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.android.application)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    js {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }
    jvm("desktop")
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//    }

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
        val desktopMain by getting
        val javaMain by getting
        val ktorMain by getting
        val ktorCioMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.animation)
            implementation(compose.animationGraphics)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(projects.core)
            implementation(projects.compose)
            implementation(projects.cli)
        }
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(androidx.activity.compose)
            implementation(ktor.client.android)
        }
        jsMain.dependencies {
            implementation(ktor.client.js)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(projects.ktor)
        }
        javaMain.dependencies {
            implementation(projects.okhttp)
            implementation(ktor.client.okhttp)
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

android {
    namespace = "hamusutax.composeApp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "hamusutax.composeApp"
        minSdk = libs.versions.androidCompose.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "hamusutax.composeApp"
            packageVersion = "1.0.0"
        }
    }
}
