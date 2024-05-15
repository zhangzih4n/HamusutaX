plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(projects.core)
    implementation(libs.kotlinx.io)
}

publishing {
    publications.register<MavenPublication>("mavenJava") {
        artifactId = "jvm"
        afterEvaluate {
            from(components["java"])
        }
    }
}
