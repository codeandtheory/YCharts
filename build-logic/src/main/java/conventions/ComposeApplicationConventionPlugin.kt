package conventions

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import ytemplate.android.configureComposeApplication

/**
 * Compose application convention plugin
 *
 * @constructor Create empty Compose application convention plugin
 */
class ComposeApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
            pluginManager.apply(libs.findPlugin("android.application").get().get().pluginId)
            val extension = extensions.getByType<ApplicationExtension>()
            configureComposeApplication(extension)
        }
    }

}
