import com.frensky.porto.buildsrc.project.Modules
import com.frensky.porto.buildsrc.utils.withClosure
import com.frensky.porto.buildsrc.utils.withDependenciesClosure

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.frensky.porto.base"

    withClosure(project.ext["defaultLogicSetting"])
    withClosure(project.ext["buildTypeModuleConfig"])
    withClosure(project.ext["defaultVariantConfig"])

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    withDependenciesClosure(project.ext["coreNativeViewDependencies"])
    withDependenciesClosure(project.ext["composeDependencies"])
    withDependenciesClosure(project.ext["unitTestDependencies"])

    implementation(project(Modules.Common.model))
}

