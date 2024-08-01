package com.aexp

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class GreetingPluginTest {
    @TempDir
    lateinit var testProjectDir: File
    private lateinit var settingsFile: File
    private lateinit var buildFile: File

    @BeforeEach
    fun setup() {
        settingsFile = File(testProjectDir, "settings.gradle.kts")
        buildFile = File(testProjectDir, "build.gradle.kts")
    }

//    @Test
    fun test() {
        val buildFileContent = """
         plugins {
            id("com.aexp.greeting") version "1.0-SNAPSHOT"
         }
         repositories {
             mavenLocal()
             maven{
                 url = uri("file://Users/vravi20/.m2/repository/")
             }
         }
      """.trimIndent()

        val settingsFileContent = """
         pluginManagement{
             repositories {
                 gradlePluginPortal()
                 mavenLocal()
                 maven {
                     url = uri("file://Users/vravi20/.m2/repository/")
                 }
             }
         }
         rootProject.name = "test-plugin"
         """.trimIndent()

        buildFile.writeText(buildFileContent)
        settingsFile.writeText(settingsFileContent)

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withArguments("greet")
            .withPluginClasspath()
            .build()
        assertTrue(result.output.contains("Hello!"))
    }
}