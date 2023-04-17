package conventions

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.file.FileTree
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPlugin
import org.gradle.testing.jacoco.tasks.JacocoReport
import ytemplate.android.jacoco.extractTestCoverage
import ytemplate.android.jacoco.jacoco

/**
 * Project jacoco convention plugin
 *
 * @constructor Create empty Library jacoco convention plugin
 */
@Suppress("UNCHECKED_CAST")
class ProjectJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jacoco")
            }

            tasks.register<JacocoReport>("createMergedJacocoReport") {
                val jacocoReport = this
                group = "Reporting"
                description = "create Project Jacoco Report for debug builds for all submodules with jacoco plugin"
                logger.quiet("======Merging HTML Reports=========")
                val javaClasses: MutableCollection<FileTree> = mutableListOf()
                val kotlinClasses: MutableCollection<FileTree> = mutableListOf()
                val sourceDir: MutableCollection<String> = mutableListOf()
                val coverageFiles: MutableCollection<String> = mutableListOf()

                subprojects {
                    val subProject = this
                    subProject.plugins.withType<JacocoPlugin>().configureEach {
                        val moduleTask = tasks.findByName("createDemoDebugJacocoReport")
                        jacocoReport.dependsOn(moduleTask)
                    }
                    if(subProject.plugins.findPlugin(JacocoPlugin::class.java)!=null) {
                        val excludedFiles: MutableCollection<String> = mutableListOf()
                        if (subProject.extra.has("excludes")) {
                            excludedFiles.addAll(subProject.extra.get("excludes") as List<String>)
                        }
                        javaClasses.add(fileTree("${subProject.buildDir}/intermediates/javac/demoDebug/classes") {
                            if (excludedFiles.isNotEmpty()) {
                                exclude(excludedFiles)
                            }

                        }.asFileTree)
                        kotlinClasses.add(fileTree("${subProject.buildDir}/tmp/kotlin-classes/demoDebug") {
                            if (excludedFiles.isNotEmpty()) {
                                exclude(excludedFiles)
                            }
                        }.asFileTree)

                        sourceDir.add("${subProject.projectDir}/src/main/java")
                        sourceDir.add("${subProject.projectDir}/src/main/kotlin")
                        sourceDir.add("${subProject.projectDir}/src/demoDebug/java")
                        coverageFiles.add("${subProject.buildDir}/outputs/unit_test_code_coverage/demoDebugUnitTest/testDemoDebugUnitTest.exec")
                        coverageFiles.add("${subProject.buildDir}/outputs/code_coverage/demoDebugAndroidTest/connected/coverage.ec")
                    }
                }
                classDirectories.setFrom(files(javaClasses, kotlinClasses))
                additionalClassDirs.setFrom(files(sourceDir))
                sourceDirectories.setFrom(files(sourceDir))
                executionData.setFrom(files(coverageFiles))
                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }
            }
        }
    }
}
