plugins {
    id("ycharts.android.application")
    id("ycharts.android.application.compose")
    id("ycharts.android.test")
}

android {
    namespace = "com.example.piechartcontainer"
    defaultConfig {
        applicationId = "com.example.piechartcontainer"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation(project(":YChartsLib"))
}
