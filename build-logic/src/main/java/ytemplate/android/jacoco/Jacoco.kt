package ytemplate.android.jacoco

import com.android.build.gradle.BaseExtension
import groovy.xml.XmlSlurper
import groovy.xml.slurpersupport.NodeChild
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File
import java.util.*
import kotlin.math.roundToInt

private val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Not an Android module: $name")

val Project.jacoco: JacocoPluginExtension
    get() = extensions.findByName("jacoco") as? JacocoPluginExtension
        ?: error("Not a Jacoco module: $name")


private val excludedFiles = mutableSetOf(
    // android
    "**/BR.*",
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "**/*ComposableSingletons*.*",
    "**/*Preview*",
    "android/**/*.*",
    // dagger
    "**/*_MembersInjector.class",
    "**/Dagger*Component.class",
    "**/Dagger*Component\$Builder.class",
    "**/*Module_*Factory.class",
    "**hilt_aggregated_deps**",
    "**/dagger/**",
    "**/di/module/*",
    "**/*_Factory*.*",
    "**/*Module*.*",
    "**/*Dagger*.*",
    "**/*dagger*.*",
    "**/*Hilt*.*",
    // kotlin
    "**/*MapperImpl*.*",
    "**/*\$ViewInjector*.*",
    "**/*\$ViewBinder*.*",
    "**/BuildConfig.*",
    "**/*Component*.*",
    "**/*\$Lambda$*.*",
    "**/*Companion*.*",
    "**/*Module*.*",
    "**/*MembersInjector*.*",
    "**/*_MembersInjector.class",
    "**/*_Factory*.*",
    "**/*_Provide*Factory*.*",
    "**/*Extensions*.*",
    // sealed and data classes
    "**/*\$AppResult.*",
    "**/*\$AppResult$*.*",
)

private val moduleLimits = mutableMapOf(
    "instruction" to 0.0,
    "branch" to 0.0,
    "line" to 0.0,
    "complexity" to 0.0,
    "method" to 0.0,
    "class" to 0.0
)


fun Project.setModuleTestCoverageLimits(modulelimits: Map<String, Double>? = null) {
    if (modulelimits != null) {
        extra.set("limits", modulelimits)
    } else {
        extra.set("limits", moduleLimits)
    }
}

fun Project.addExclusion(excludes: Set<String>? = null) {
    if (excludes != null) {
        excludedFiles.addAll(excludes)
    }
    extra.set("excludes", excludedFiles.toList())
}

internal fun Project.setupJacocoPlugin() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco").get().toString()
    }
    if (!extra.has("limits")) {
        setModuleTestCoverageLimits()
    }


    val buildTypes = android.buildTypes.map { type -> type.name }
    var productFlavors = android.productFlavors.map { flavor -> flavor.name }

    if (productFlavors.isEmpty()) {
        productFlavors = productFlavors + ""
    }
    productFlavors.forEach { flavorName ->
        buildTypes.forEach { buildTypeName ->
            val sourceName: String
            val sourcePath: String
            if (flavorName.isEmpty()) {
                sourceName = buildTypeName
                sourcePath = buildTypeName
            } else {
                sourceName = "${flavorName}${buildTypeName.capitalized()}"
                sourcePath = "${flavorName}${buildTypeName.capitalized()}"
            }
            val testTaskName = "test${sourceName.capitalized()}UnitTest"
            val androidTestTaskName = "create${sourceName.capitalized()}CoverageReport"
            addTestCoverageTask(
                testTaskName,
                androidTestTaskName,
                sourceName,
                sourcePath,
                flavorName,
                buildTypeName
            )
        }
    }
}

internal fun Project.setupKotlinJacocoPlugin() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco").get().toString()
    }
    extra.set("limits", moduleLimits)
    extra.set("excludes", excludedFiles.toList())
    val sourceName = "DemoDebug"
    tasks.register<JacocoReport>("create${sourceName.capitalized()}JacocoReport") {
        dependsOn("test", "jacocoTestReport")
        group = "Reporting"
        description = "Generate test coverage reports on the ${sourceName.capitalized()} build"
        val classFiles = fileTree("${project.buildDir}/classes/kotlin/jvm/main/") {
            exclude(excludedFiles)
        }
        classDirectories.setFrom(files(classFiles))
        val srcFileDir = fileTree("src/") { exclude(listOf("*Test/**", "*Test/**")) }
        sourceDirectories.setFrom(files(srcFileDir))
        additionalClassDirs.setFrom(files(srcFileDir))
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
        doLast {
            jacocoTestReport("create${sourceName.capitalized()}JacocoReport")
        }
    }
}

private fun Project.addTestCoverageTask(
    taskName: String,
    androidTestTaskName: String,
    sourceName: String,
    sourcePath: String,
    flavorName: String,
    buildTypeName: String
) {
    tasks.register<JacocoReport>("create${sourceName.capitalized()}JacocoReport") {
        dependsOn(taskName, androidTestTaskName)
        group = "Reporting"
        description = "Generate test coverage reports on the ${sourceName.capitalized()} build"
        val javaDirectories =
            fileTree("${buildDir}/intermediates/javac/$sourcePath/classes") {
                exclude(excludedFiles)
            }
        val kotlinDirectories = fileTree("${buildDir}/tmp/kotlin-classes/$sourcePath") {
            exclude(excludedFiles)
        }
        val coverageDirectories = listOf(
            "$projectDir/src/main/java",
            "$projectDir/src/main/kotlin",
            "$projectDir/src/$flavorName/java",
            "$projectDir/src/$buildTypeName/java"
        )
        val androidTestsData =
            fileTree("${buildDir}/outputs/code_coverage/${sourcePath}AndroidTest/connected/") {
                include(listOf("**/*.ec"))
                exclude(excludedFiles)
            }
        classDirectories.setFrom(files(javaDirectories, kotlinDirectories))
        additionalClassDirs.setFrom(files(coverageDirectories))
        sourceDirectories.setFrom(files(coverageDirectories))
        executionData.setFrom(
            files("${buildDir}/outputs/unit_test_code_coverage/${sourceName}UnitTest/$taskName.exec"),
            androidTestsData.files
        )

        reports {
            xml.required.set(true)
            html.required.set(true)
        }

        doLast {
            jacocoTestReport("create${sourceName.capitalized()}JacocoReport")
        }
    }
}

 fun Project.jacocoTestReport(taskName: String) {
    val reportDir = jacoco.reportsDirectory.asFile.get()
    val report = file("$reportDir/$taskName/${taskName}.xml")
    logger.lifecycle("Checking coverage results:$report")
    val metrics = report.extractTestCoverage()
    val limits = project.extra["limits"] as Map<String, Double>
    val failures = metrics.filter { item ->
        item.value < limits[item.key]!!
    }.map { item ->
        "-${item.key} coverage is: ${item.value}%, minimum is ${limits[item.key]}%"
    }
    if (failures.isNotEmpty()) {
        logger.quiet("======Code coverage failed=========")
        failures.forEach {
            logger.quiet(it)
        }
        logger.quiet("===========================================")
    } else {
        logger.quiet("======Code coverage success=========")
        metrics.forEach {
            logger.quiet("- ${it.key} coverage: ${it.value}")
        }
        logger.quiet("===========================================")
    }

}

fun File.extractTestCoverage(): Map<String, Double> {
    val xmlReader = XmlSlurper().apply {
        setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
    }
    val counterNodes: List<NodeChild> = xmlReader.parse(this).parent().children()
        .filter { (it as NodeChild).name() == "counter" } as List<NodeChild>
    return counterNodes.associate { child ->
        val type = child.attributes()["type"].toString().toLowerCase(Locale.US)
        val covered = child.attributes()["covered"].toString().toDouble()
        val missed = child.attributes()["missed"].toString().toDouble()
        val percentage = ((covered / (covered + missed)) * 10000.0).roundToInt() / 100.0
        Pair(type, percentage)
    }
}
