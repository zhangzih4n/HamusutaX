enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
            content {
                includeGroupByRegex("com\\.github\\..+")
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
            content {
                includeGroupByRegex("com\\.github\\..+")
            }
        }
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
            content {
                includeModule("androidx.compose.compiler", "compiler")
            }
        }
    }
}

rootProject.name = "HamusutaX"
include(":shared")
include(":android")
include(":jvm")
