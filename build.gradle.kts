plugins {
    `java-library`
    `maven-publish`
}

group = "me.jakepronger.spine"
version = "1.0.8"

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

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("maven") {
                from(components["java"])

                groupId = gitGroup
                artifactId = artifactId
                version = sVersion
            }
        }
    }
}
