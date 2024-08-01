plugins {
    application
    `kotlin-dsl`
    `maven-publish`
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
}

group = "com.aexp"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("gradle-archetype") {
            id = "com.aexp.archetype"
            implementationClass = "com.aexp.gradle.plugin.archetype.ArchetypePlugin"
        }
    }
}

dependencies {
    implementation("com.charleskorn.kaml:kaml:0.60.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    testImplementation(gradleTestKit())
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

publishing {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}