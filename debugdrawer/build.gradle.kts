plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
    id("jacoco")
    id("maven-publish")
}

android {
    namespace = "com.abualzait.debugdrawer"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    // Android Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.8.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.48")
}

ktlint {
    version.set("0.50.0")
    debug.set(true)
    verbose.set(true)
    android.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    enableExperimentalRules.set(true)
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$projectDir/../detekt.yml")
}

// JaCoCo configuration
jacoco {
    toolVersion = "0.8.11"
}

// Maven publishing configuration for JitPack
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.abualzait"
                artifactId = project.findProperty("POM_ARTIFACT_ID") as String? ?: "debugdrawer"
                version = project.findProperty("VERSION_NAME") as String? ?: "1.0.0"

                pom {
                    name.set(project.findProperty("POM_NAME") as String? ?: "Android Debug Drawer")
                    description.set(project.findProperty("POM_DESCRIPTION") as String? ?: "A comprehensive, production-ready debug drawer for Android apps")
                    url.set(project.findProperty("POM_URL") as String? ?: "https://github.com/mabualzait/Android-Debug-Drawer")

                    licenses {
                        license {
                            name.set(project.findProperty("POM_LICENCE_NAME") as String? ?: "MIT")
                            url.set(project.findProperty("POM_LICENCE_URL") as String? ?: "https://opensource.org/licenses/MIT")
                            distribution.set(project.findProperty("POM_LICENCE_DIST") as String? ?: "repo")
                        }
                    }

                    developers {
                        developer {
                            id.set(project.findProperty("POM_DEVELOPER_ID") as String? ?: "mabualzait")
                            name.set(project.findProperty("POM_DEVELOPER_NAME") as String? ?: "Malik Abualzait")
                            url.set(project.findProperty("POM_DEVELOPER_URL") as String? ?: "https://github.com/mabualzait")
                        }
                    }

                    scm {
                        url.set(project.findProperty("POM_SCM_URL") as String? ?: "https://github.com/mabualzait/Android-Debug-Drawer")
                        connection.set(project.findProperty("POM_SCM_CONNECTION") as String? ?: "scm:git:git://github.com/mabualzait/Android-Debug-Drawer.git")
                        developerConnection.set(project.findProperty("POM_SCM_DEV_CONNECTION") as String? ?: "scm:git:ssh://github.com/mabualzait/Android-Debug-Drawer.git")
                    }
                }
            }
        }
    }
}
