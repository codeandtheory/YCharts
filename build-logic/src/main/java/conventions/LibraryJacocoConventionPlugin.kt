package conventions

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import ytemplate.android.jacoco.setupJacocoPlugin

/**
 * Library jacoco convention plugin
 *
 * @constructor Create empty Library jacoco convention plugin
 */
class LibraryJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jacoco")
            }
            extensions.configure<LibraryExtension> {
                buildTypes {
                    getByName("debug") {
                        isMinifyEnabled = false
                        enableUnitTestCoverage = true
                        enableAndroidTestCoverage = true
                    }

                    testOptions {
                        animationsDisabled = true
                        unitTests {
                            isIncludeAndroidResources = true
                            isReturnDefaultValues = true
                        }
                    }
                }
                setupJacocoPlugin()

            }
        }
    }
}
