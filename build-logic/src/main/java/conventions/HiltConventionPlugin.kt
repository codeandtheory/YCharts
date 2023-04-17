package conventions

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Hilt convention plugin
 *
 * @constructor Create empty Hilt convention plugin
 */
class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")

            with(pluginManager) {
                apply(libs.findPlugin("hilt").get().get().pluginId)
                // KAPT must go last to avoid build warnings.
                // See: https://stackoverflow.com/questions/70550883/warning-the-following-options-were-not-recognized-by-any-processor-dagger-f
                apply(libs.findPlugin("kapt").get().get().pluginId)
            }

            dependencies {
                "implementation"(libs.findBundle("hilt").get())
                "kapt"(libs.findLibrary("hilt.compiler").get())
                "kaptAndroidTest"(libs.findLibrary("hilt.compiler").get())
                "androidTestImplementation"(libs.findLibrary("hilt.test").get())
            }

        }
    }

}