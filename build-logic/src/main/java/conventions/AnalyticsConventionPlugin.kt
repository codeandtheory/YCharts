package conventions

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AnalyticsConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
            dependencies {
                add("implementation", libs.findLibrary("kotlin.coroutines.core").get())
                add("implementation", libs.findLibrary("kotlin.stdlib").get())
            }

        }
    }
}