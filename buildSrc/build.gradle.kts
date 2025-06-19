plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    runtimeOnly(libs.classpath.android.gradle)
    implementation(libs.classpath.kotlin.gradle)
    implementation("com.squareup:javapoet:1.13.0")
}