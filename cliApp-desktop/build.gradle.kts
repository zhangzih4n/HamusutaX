import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlin.plugin.allopen)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
    alias(kotlinx.plugins.benchmark)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.core)
    implementation(projects.cliApp)
    implementation(kotlinx.coroutines.core)
    api(libs.kevinnzou.compose.webview)
}

application {
    mainClass.set("MainKt")
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

benchmark {
    targets {
        register("main")
    }
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
