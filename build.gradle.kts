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
        //classpath("org.jetbrains.kotlin:kotlin-android-extensions:2.2.0")
    }

}
plugins {
    alias(versionCatalogLibs.plugins.dokka) apply false
    alias(versionCatalogLibs.plugins.kotlin.compose) apply false
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}

