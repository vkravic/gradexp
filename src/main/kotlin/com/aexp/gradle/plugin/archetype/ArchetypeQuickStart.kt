package com.aexp.gradle.plugin.archetype

import com.aexp.gradle.plugin.models.ArcheModel
import com.aexp.gradle.plugin.models.loadArcheModel
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.copyToRecursively

open class ArchetypeQuickStart : DefaultTask() {
    init {
        description = "Sample project from quickstart plugin"
    }

    @OptIn(ExperimentalPathApi::class)
    @TaskAction
    fun create() {
        println("Creating a new project in "+project.projectDir)
        println("Enter the project name: ")
        var projectName:String = readln()
        println("Enter the project group id: ")
        var groupId:String = readln()
        println("Enter the project version: ")
        var version:String = readln()
        println("Enter the project language: ")
        var lang:String = readln()
        println("Enter the project build language: ")
        var buildLang:String = readln()
        println("Enter the project archetype: ")
        var archetype:String = readln()
        println("Creating project $projectName with group id $groupId and version $version")
        var archeModel:ArcheModel = loadArcheModel()
        archeModel.artifact_id = projectName.isNotBlank().let { projectName.lowercase() }
        archeModel.group_id = groupId.isNotBlank().let { groupId.lowercase() }
        archeModel.version = version.isNotBlank().let { version.lowercase() }
        archeModel.build_lang = buildLang.isNotBlank().let { buildLang.lowercase() }
        archeModel.prog_lang = lang.isNotBlank().let { lang.lowercase() }

        Files.createDirectories(Paths.get(project.projectDir.absolutePath+"/${archeModel.artifact_id}/src/main/${archeModel.prog_lang}"))
        Files.createDirectories(Paths.get(project.projectDir.absolutePath+"/${archeModel.artifact_id}/src/main/resources"))
        Files.createDirectories(Paths.get(project.projectDir.absolutePath+"/${archeModel.artifact_id}/src/test/${archeModel.prog_lang}"))

        val destinationFile = File(project.projectDir.absolutePath+"/${archeModel.artifact_id}/build.gradle.kts")
        val rootContent = ArcheModel::class.java.getResourceAsStream("/archetype-resources/buildfiles/${archeModel.build_lang}/build.gradle.kts")
        rootContent.use { input ->
            destinationFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

//        rootContent.walk().forEach {
//            println(it)
//        }

//        var source = File(ArchetypeQuickStart::class.java.getResource("/archetype-resources/buildfiles/${archeModel.build_lang}/").toURI())
//
//        source.copyToRecursively(Path(project.projectDir.absolutePath+"/${archeModel.artifact_id}"), followLinks = false, overwrite = true)
//
//        source = Paths.get(ArchetypeQuickStart::class.java.getResource("/archetype-resources/buildfiles/${archeModel.prog_lang}/").toURI())
//        source.copyToRecursively(Path(project.projectDir.absolutePath+"/${archeModel.artifact_id}/src/main/${archeModel.prog_lang}"), followLinks = false, overwrite = true)
    }


    fun copyFileFromResources(resourceName: String, targetDirectory: String) {
        val resource = this::class.java.classLoader.getResourceAsStream(resourceName)
            ?: throw IllegalArgumentException("Resource not found: $resourceName")

        val targetFile = File(targetDirectory, resourceName)
        targetFile.parentFile.mkdirs() // Create parent directories if needed

        resource.use { input ->
            targetFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }
}
