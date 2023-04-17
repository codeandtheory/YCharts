package conventions

import org.gradle.api.Plugin
import org.gradle.api.Project
import ytemplate.android.jacoco.setupKotlinJacocoPlugin

/**
 * Library kotlin jacoco convention plugin
 *
 * @constructor Create empty Kotlin Library jacoco convention plugin
 */
class KotlinLibraryJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jacoco")
            }
            setupKotlinJacocoPlugin()

        }
    }
}
