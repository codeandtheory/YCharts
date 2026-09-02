pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
    }
    versionCatalogs {
        create("versionCatalogLibs") {
            from(files("./gradle/libs.versions.toml"))
        }
    }
}
rootProject.name = "YCharts"
include(
    ":app",
    ":YChartsLib",
    ":experiments:chartcontainer",
    ":experiments:piechartcontainer"
)
