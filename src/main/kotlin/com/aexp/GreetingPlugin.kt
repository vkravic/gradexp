package com.aexp
import org.gradle.api.Plugin
import org.gradle.api.Project

import org.gradle.kotlin.dsl.*


class GreetingPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        val greeting = extensions.create("greeting", Greeting::class)
        tasks {
            register("greet") {
                doLast {
                    println(greeting.message)
                }
            }
        }
    }
}


open class Greeting {
    var message = "Hello!"
}