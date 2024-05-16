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
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
}

rootProject.name = "HamusutaX"
include(":core")
include(":compose")
include(":okhttp")
include(":android-compose")
