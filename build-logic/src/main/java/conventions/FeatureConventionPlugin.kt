package conventions

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

/**
 * Feature convention plugin
 *
 * @constructor Create empty Feature convention plugin
 */
class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")

            pluginManager.apply {
                apply("ytemplate.android.library")
                apply("ytemplate.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "ytemplate.android.core.test.HiltTestRunner"
                }
            }

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:data"))

                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":core:test"))
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", project(":core:test"))
                add("implementation", libs.findLibrary("hilt.nav.compose").get())
                add("implementation", libs.findLibrary("runtime.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("coroutine").get())
            }
        }
    }
}
