// Module Domain : Kotlin pur, sans Android, il ne dépend d'aucun autre module.
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    // api : Flow apparaît dans les interfaces du Domain, data et app doivent le voir
    api(libs.kotlinx.coroutines.core)
}
