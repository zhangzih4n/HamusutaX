@file:Suppress("UnstableApiUsage")

rootProject.name = "hamusutax"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jitpack.io")
            content {
                includeGroupAndSubgroups("com.github")
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://jitpack.io/")
            mavenContent { includeGroupAndSubgroups("com.github") }
        }
        maven("https://s01.oss.sonatype.org/content/repositories/releases/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap/")
            mavenContent { includeGroup("io.ktor") }
        }
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
            mavenContent {
                includeModule("androidx.compose.compiler", "compiler")
            }
        }
        maven {
            url = uri("https://jogamp.org/deployment/maven")
            mavenContent {
                includeGroupAndSubgroups("org.jogamp")
            }
        }
        mavenLocal()
    }

    versionCatalogs {
        create("jetbrains") {
            from(files("gradle/jetbrains.versions.toml"))
        }
        create("androidx") {
            from(files("gradle/androidx.versions.toml"))
        }
        create("google") {
            from(files("gradle/google.versions.toml"))
        }
        create("ktor") {
            from(files("gradle/ktor.versions.toml"))
        }
        create("squareup") {
            from(files("gradle/squareup.versions.toml"))
        }
        create("koin") {
            from(files("gradle/koin.versions.toml"))
        }
        create("bouncycastle") {
            from(files("gradle/bouncycastle.versions.toml"))
        }
        create("kotlincrypto") {
            from("org.kotlincrypto:version-catalog:0.5.2")
        }
    }
}

include(":core")
include(":compose")
include(":ktor")
include(":okhttp")
include(":okio")
include(":room")
include(":androidx")
include(":crypto")
include(":jsoup")
include(":cli")
include(":clients-alist-ktor")
include(":formats-bencode")
include(":formats-bittorrent")
include(":formats-ebml")
include(":formats-gramps")
include(":cliApp")
include(":cliApp-desktop")
include(":composeApp")
include(":composeApp-desktop")
