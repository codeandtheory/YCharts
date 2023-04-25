pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
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
