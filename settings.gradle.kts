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
        gradlePluginPortal()
        mavenCentral()
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
            url = uri("https://jitpack.io")
            mavenContent {
                includeGroupAndSubgroups("com.github")
            }
        }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/releases/") }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
            mavenContent {
                includeModule("androidx.compose.compiler", "compiler")
            }
        }
        mavenLocal()
    }
}

rootProject.name = "HamusutaX"
include(":core")
include(":compose")
