plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(jetbrains.plugins.compose) apply false
    alias(jetbrains.plugins.kotlin.multiplatform) apply false
    alias(jetbrains.plugins.kotlin.android) apply false
    alias(jetbrains.plugins.kotlin.jvm) apply false
    alias(jetbrains.plugins.kotlin.plugin.serialization) apply false
}
