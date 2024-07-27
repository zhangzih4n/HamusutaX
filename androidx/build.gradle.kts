import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.android.library)
    id("maven-publish")
}

android {
    namespace = "hamusutax"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources.excludes += "DebugProbesKt.bin"
    }
}

dependencies {
    implementation(androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(androidx.junit)
    androidTestImplementation(androidx.espresso.core)
    implementation(projects.core)
    implementation(kotlinx.datetime)
    implementation(kotlinx.io.core)
    implementation(kotlinx.io.bytestring)
    implementation(kotlinx.serialization.json)
    implementation(androidx.browser)
}

tasks.withType<KotlinCompile> {
    compilerOptions.optIn.addAll(
        "kotlin.ExperimentalStdlibApi",
        "kotlin.ExperimentalUnsignedTypes",
        "kotlin.contracts.ExperimentalContracts",
        "kotlin.io.encoding.ExperimentalEncodingApi",
        "kotlin.time.ExperimentalTime",
        "kotlinx.coroutines.ExperimentalCoroutinesApi",
        "kotlinx.datetime.format.FormatStringsInDatetimeFormats",
        "kotlinx.serialization.ExperimentalSerializationApi",
    )
}
