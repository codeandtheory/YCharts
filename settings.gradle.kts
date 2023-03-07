pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "YCharts"
include(
    ":androidApp",
    ":YChartsLib",
    ":experiments:chartcontainer",
    ":experiments:piechartcontainer",
    ":KMMYCharts"
)
