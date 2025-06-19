// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(
        libs.plugins.androidApplication
            .get()
            .pluginId,
    ) apply false
    id(
        libs.plugins.kotlinAndroid
            .get()
            .pluginId,
    ) apply false
    id(
        libs.plugins.androidLibrary
            .get()
            .pluginId,
    ) apply false

    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hiltAndroid) apply false
    alias(libs.plugins.compose.compiler) apply false
}

allprojects {
    apply(from = "${rootProject.projectDir}/config.gradle")
    apply(from = "${rootProject.projectDir}/depedencies.gradle")
    apply(from = "${rootProject.projectDir}/flavor.gradle")
}

subprojects {
    afterEvaluate {

    }
}