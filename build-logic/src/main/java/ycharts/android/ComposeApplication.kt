package ycharts.android

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Configure compose application using non-generic BaseExtension
 */
internal fun Project.configureComposeApplication(
    extension: ApplicationExtension,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")

    extension.buildFeatures.compose = true
    extension.composeOptions.kotlinCompilerExtensionVersion =
        extensions.getByType<VersionCatalogsExtension>()
            .named("versionCatalogLibs")
            .findVersion("androidxComposeCompiler")
            .get()
            .toString()

    configureComposeDependencies()
}

/**
 * Configure compose for Library modules (like YChatrsLib)
 */
internal fun Project.configureComposeLibrary(
    extension: LibraryExtension,
) {
    extension.buildFeatures.compose = true
    extension.composeOptions.kotlinCompilerExtensionVersion =
        extensions.getByType<VersionCatalogsExtension>()
            .named("versionCatalogLibs")
            .findVersion("androidxComposeCompiler")
            .get()
            .toString()

    configureComposeDependencies()
}

private fun Project.configureComposeDependencies() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("versionCatalogLibs")
    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("implementation", libs.findBundle("compose").get())
        add("androidTestImplementation", platform(bom))
        add("androidTestImplementation", libs.findLibrary("androidx.compose.ui.test").get())
        add("debugImplementation", libs.findLibrary("androidx.compose.ui-manifest-test").get())
    }
}