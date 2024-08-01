package com.aexp.gradle.plugin.models

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.*
import java.io.File

@Serializable
data class Team(
    val leader: String,
    val members: List<String>
)

fun main(args: Array<String>) {
//    val input = """
//        leader: Amy
//        members:
//          - Bob
//          - Cindy
//          - Dan
//    """.trimIndent()

    val rootContent = Team::class.java.getResource("/rootModel.yml")?.readText(Charsets.UTF_8)

    val result: Team = Yaml.default.decodeFromString(Team.serializer(), rootContent!!)

    println(result.members[1])
}
