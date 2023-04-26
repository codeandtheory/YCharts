package conventions

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

/**
 * Android test convention plugin
 *
 * @constructor Create empty Android test convention plugin
 */
class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
            dependencies {
                add("testImplementation", libs.findBundle("test").get())
                add("testImplementation", libs.findBundle("mock").get())
                add("testImplementation", libs.findBundle("coroutine.test").get())
                add("testImplementation", libs.findLibrary("androidx.junit").get())
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
            }

        }
    }

}
