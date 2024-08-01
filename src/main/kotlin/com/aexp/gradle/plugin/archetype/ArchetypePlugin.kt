package com.aexp.gradle.plugin.archetype

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.properties.GradleProperties

class ArchetypePlugin : Plugin<Project> {
    /**
     * Apply this plugin to the given target object.
     *
     * @param project The target object
     */
    override fun apply(project: Project) {
//        val extension = project.extensions.create(
//            EXTENSION_NAME,
//            ArchetypeExtension::class.java,
//            project)

        val quickstart = project.tasks.register(QUICKSTART, ArchetypeQuickStart::class.java) {
            dependsOn("wrapper")
            doFirst {
                System.setProperty("org.gradle.logging.level","quiet")
            }
        }
    }

    companion object {
        const val EXTENSION_NAME = "archetype"
        const val QUICKSTART = "quickStart"
    }

}