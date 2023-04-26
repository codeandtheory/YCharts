plugins {
    `kotlin-dsl`
}

group = "ycharts.android.buildlogic"

repositories {
    mavenCentral()
    google()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(versionCatalogLibs.android.gradle.plugin)
    compileOnly(versionCatalogLibs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "ycharts.android.application"
            implementationClass = "conventions.ApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "ycharts.android.application.compose"
            implementationClass = "conventions.ComposeApplicationConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "ycharts.android.library.compose"
            implementationClass = "conventions.LibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "ycharts.android.library"
            implementationClass = "conventions.LibraryConventionPlugin"
        }

        register("androidTest") {
            id = "ycharts.android.test"
            implementationClass = "conventions.AndroidTestConventionPlugin"
        }
    }
}
