buildscript {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath(versionCatalogLibs.android.gradle.plugin)
        classpath(versionCatalogLibs.kotlin.gradle.plugin)
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:2.2.0")
    }

    tasks {
        register("clean", Delete::class) {
            delete(rootProject.buildDir)
        }
    }
}
plugins {
    id("org.jetbrains.dokka") version "2.2.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10" apply false // Use your current Kotlin version
}

