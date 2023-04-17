package conventions

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import ytemplate.android.configureComposeApplication

/**
 * Library compose convention plugin
 *
 * @constructor Create empty Library compose convention plugin
 */
class LibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
            pluginManager.apply(libs.findPlugin("android.library").get().get().pluginId)
            val extension = extensions.getByType<LibraryExtension>()
            configureComposeApplication(extension)
        }
    }

}
