import com.frensky.porto.buildsrc.project.Modules
import com.frensky.porto.buildsrc.utils.withClosure
import com.frensky.porto.buildsrc.utils.withDependenciesClosure

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.frensky.porto"

    withClosure(project.ext["defaultConfigAppSetting"])
    withClosure(project.ext["buildTypeConfig"])
    withClosure(project.ext["appSiginingKeyConfig"])

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            signingConfig = null
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.process)
    withDependenciesClosure(project.ext["commonDependencies"])
    withDependenciesClosure(project.ext["workerHiltDependencies"])
    withDependenciesClosure(project.ext["coreNativeViewDependencies"])
    withDependenciesClosure(project.ext["composeDependencies"])
    withDependenciesClosure(project.ext["unitTestDependencies"])

    implementation(project(Modules.Common.domain))
    implementation(project(Modules.Common.base))
    implementation(project(Modules.Common.data))
    implementation(project(Modules.Common.compose))
    implementation(project(Modules.Common.model))

    implementation(libs.gson)
}