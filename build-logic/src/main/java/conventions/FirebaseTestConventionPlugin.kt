package conventions

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
/**
 * Firebase test convention plugin
 *
 * @constructor Create empty Firebase test convention plugin
 */
class FirebaseTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
            pluginManager.apply {
                apply("ytemplate.android.library")
            }

            extensions.configure<LibraryExtension> {
                packagingOptions {
                    resources {
                        excludes += "/META-INF/*"
                    }
                }
                defaultConfig.targetSdk = 33
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            dependencies {
                add("implementation", libs.findLibrary("kotlin.common.test").get())
                add("testImplementation", libs.findLibrary("jupiter.junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
                add("androidTestImplementation", libs.findLibrary("anotation").get())
                add("androidTestImplementation", libs.findLibrary("mockk.android").get())
                add("androidTestImplementation", libs.findLibrary("test.runner").get())
            }

        }
    }
}
