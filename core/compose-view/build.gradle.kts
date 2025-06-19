import com.frensky.porto.buildsrc.project.Modules
import com.frensky.porto.buildsrc.utils.withClosure
import com.frensky.porto.buildsrc.utils.withDependenciesClosure

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.frensky.porto.compose"

    withClosure(project.ext["defaultConfigFeatureSetting"])
    withClosure(project.ext["buildTypeConfig"])
    withClosure(project.ext["defaultVariantConfig"])

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    withDependenciesClosure(project.ext["commonDependencies"])
    withDependenciesClosure(project.ext["composeDependencies"])

    implementation(project(Modules.Common.base))
    implementation(project(Modules.Common.domain))
}