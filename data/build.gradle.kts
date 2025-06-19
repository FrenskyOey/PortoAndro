import com.frensky.porto.buildsrc.project.Modules
import com.frensky.porto.buildsrc.utils.withClosure
import com.frensky.porto.buildsrc.utils.withDependenciesClosure

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.frensky.porto.data"
    withClosure(project.ext["defaultLogicSetting"])
    withClosure(project.ext["buildTypeConfig"])
    withClosure(project.ext["dataVariantConfig"])

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    configurations.all {
        resolutionStrategy {
            force("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
            force("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
        }
    }

    withDependenciesClosure(project.ext["commonDependencies"])
    withDependenciesClosure(project.ext["dataDependencies"])
    withDependenciesClosure(project.ext["unitTestDependencies"])

    implementation(libs.androidx.dataStore.preferences)

    implementation(project(Modules.Common.domain))
    implementation(project(Modules.Common.model))
}
