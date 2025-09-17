// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.4" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1" apply false
    id("jacoco") apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

// Root-level JaCoCo configuration for multi-module coverage
apply(plugin = "jacoco")

jacoco {
    toolVersion = "0.8.11"
}

tasks.register<JacocoReport>("jacocoRootReport") {
    group = "Reporting"
    description = "Generate Jacoco coverage reports for all modules"
    
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    
    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/di/**",
        "**/hilt/**"
    )
    
    val debugTree = fileTree("${project.rootDir}/debugdrawer/build/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }
    val sampleappTree = fileTree("${project.rootDir}/sampleapp/build/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }
    
    classDirectories.setFrom(files(debugTree, sampleappTree))
    executionData.setFrom(fileTree("${project.rootDir}") {
        include("**/build/jacoco/*.exec")
    })
    
    dependsOn(":debugdrawer:test", ":sampleapp:test")
}
