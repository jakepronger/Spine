import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.5"
}

group = "me.jakepronger.spine"
version = "1.0.5"

val artifactId = "Spine"
val gitGroup = "com.github.jakepronger"
val sVersion = version.toString()

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

// Modern Gradle 9 approach to configure the task
tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("maven") {
                // Now that the plugin is 'com.gradleup.shadow', this component should be found
                from(components["shadow"])

                groupId = gitGroup
                artifactId = artifactId
                version = sVersion
            }
        }
    }
}
