package conventions

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import ytemplate.android.jacoco.setupJacocoPlugin

/**
 * Application jacoco convention plugin
 *
 * @constructor Create empty Application jacoco convention plugin
 */
class ApplicationJacocoConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jacoco")
            }
            extensions.configure<AppExtension> {
                buildTypes{
                    getByName("debug") {
                        isMinifyEnabled  = false
                        enableUnitTestCoverage = true
                        enableAndroidTestCoverage = true
                    }
                }
                testOptions {
                    animationsDisabled = true
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }
            }
            setupJacocoPlugin()

        }

    }
}
