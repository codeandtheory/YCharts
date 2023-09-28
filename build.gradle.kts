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
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:1.8.20")
    }

    tasks {
        register("clean", Delete::class) {
            delete(rootProject.buildDir)
        }
    }
}
plugins {
    id("org.jetbrains.dokka") version "1.9.0" apply false
}
