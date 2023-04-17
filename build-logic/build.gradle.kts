plugins {
    `kotlin-dsl`
}

group = "ytemplate.android.buildlogic"

repositories {
    mavenCentral()
    google()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(versionCatalogLibs.android.gradle.plugin)
    compileOnly(versionCatalogLibs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "ytemplate.android.application"
            implementationClass = "conventions.ApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "ytemplate.android.application.compose"
            implementationClass = "conventions.ComposeApplicationConventionPlugin"
        }

        register("androidApplicationJacoco") {
            id = "ytemplate.android.application.jacoco"
            implementationClass = "conventions.ApplicationJacocoConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "ytemplate.android.library.compose"
            implementationClass = "conventions.LibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "ytemplate.android.library"
            implementationClass = "conventions.LibraryConventionPlugin"
        }

        register("androidLibraryJacoco") {
            id = "ytemplate.android.library.jacoco"
            implementationClass = "conventions.LibraryJacocoConventionPlugin"
        }

        register("androidProjectJacoco") {
            id = "ytemplate.android.project.jacoco"
            implementationClass = "conventions.ProjectJacocoConventionPlugin"
        }

        register("kotlinLibraryJacoco") {
            id = "ytemplate.android.library.kotlin.jacoco"
            implementationClass = "conventions.KotlinLibraryJacocoConventionPlugin"
        }


        register("androidFeature") {
            id = "ytemplate.android.feature"
            implementationClass = "conventions.FeatureConventionPlugin"
        }


        register("androidHilt") {
            id = "ytemplate.android.hilt"
            implementationClass = "conventions.HiltConventionPlugin"
        }

        register("androidTest") {
            id = "ytemplate.android.test"
            implementationClass = "conventions.AndroidTestConventionPlugin"
        }

        register("analytics") {
            id = "ytemplate.android.analytics"
            implementationClass = "conventions.AnalyticsConventionPlugin"
        }

        register("analyticsTest") {
            id = "ytemplate.android.analytics.test"
            implementationClass = "conventions.AnalyticsTestConventionPlugin"
        }

        register("firebase") {
            id = "ytemplate.android.firebase"
            implementationClass = "conventions.FirebaseConventionPlugin"
        }

        register("firebaseTest") {
            id = "ytemplate.android.firebase.test"
            implementationClass = "conventions.FirebaseTestConventionPlugin"
        }
    }
}
