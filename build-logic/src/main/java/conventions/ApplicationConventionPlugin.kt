package conventions

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import ytemplate.android.configureFlavors
import ytemplate.android.configureKotlinAndroid

/**
 * Application convention plugin
 *
 * @constructor Create empty Application convention plugin
 */
class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
            with(pluginManager) {
                apply(libs.findPlugin("android.application").get().get().pluginId)
                apply(libs.findPlugin("kotlin.android").get().get().pluginId)
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                packagingOptions {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                        excludes += "META-INF/DEPENDENCIES"
                        excludes += "META-INF/gradle/*"
                    }
                }
                defaultConfig.targetSdk = 33
                configureFlavors(this)
            }
        }
    }

}
