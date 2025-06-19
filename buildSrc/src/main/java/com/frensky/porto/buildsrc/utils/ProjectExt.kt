package com.frensky.porto.buildsrc.utils

import org.gradle.api.Project

fun Project.hasEitherTask(tasks: List<String>) = tasks.any { this.gradle.taskGraph.hasTask(it) }

fun Project.isAndroidAppModule() = this.plugins.hasPlugin("com.android.application")
fun Project.isAndroidLibraryModule() = this.plugins.hasPlugin("com.android.library")
fun Project.isAndroidDynamicLibraryModule() = this.plugins.hasPlugin("com.android.dynamic-feature")
fun Project.isAndroidProjects() =
    isAndroidAppModule() || isAndroidLibraryModule() || isAndroidDynamicLibraryModule()

fun Project.isJavaModule() = this.plugins.hasPlugin("java")
fun Project.isKotlinModule() = this.plugins.hasPlugin("kotlin")
fun Project.isJavaProjects() = isJavaModule() || isKotlinModule()
