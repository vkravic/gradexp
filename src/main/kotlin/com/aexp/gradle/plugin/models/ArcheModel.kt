package com.aexp.gradle.plugin.models

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable

@Serializable
data class ArcheModel(
    var group_id: String,
    var artifact_id: String,
    var version: String,
    var build_lang: String,
    var prog_lang: String,
    val archetypes_list: List<String>
)

fun loadArcheModel(): ArcheModel {
    val rootContent = ArcheModel::class.java.getResource("/archetype-resources/rootmodel/rootModel.yml")?.readText(Charsets.UTF_8)
    val result: ArcheModel = Yaml.default.decodeFromString(ArcheModel.serializer(), rootContent!!)
    println(result.archetypes_list[1])
    return result
}
