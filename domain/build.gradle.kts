import com.frensky.porto.buildsrc.utils.withClosure
import com.frensky.porto.buildsrc.utils.withDependenciesClosure

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.frensky.porto.domain"
    withClosure(project.ext["defaultLogicSetting"])
    withClosure(project.ext["buildTypeConfig"])
    withClosure(project.ext["defaultVariantConfig"])
}

dependencies {
    implementation(libs.coroutines)
    withDependenciesClosure(project.ext["unitTestDependencies"])
}

