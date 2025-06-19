package com.frensky.porto.buildsrc.project

import org.gradle.api.Project
import java.util.Properties

class LocalProperties(project: Project) {

    private val properties = Properties()

    init {
        val file = project.file("local.properties")
        if (file.exists()) {
            properties.load(project.file(file).inputStream())
        }
    }

    fun <T> get(key: String, defaultValue: T): T {
        return (properties.getProperty(key) ?: defaultValue) as T
    }
}