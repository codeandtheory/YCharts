plugins {
    id("ycharts.android.application")
    id("ycharts.android.application.compose")
    id("ycharts.android.test")
}

android {
    namespace = "co.yml.ycharts.app"
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
        applicationId = "co.yml.ycharts.app"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":YChartsLib")))
}
