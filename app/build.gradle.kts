plugins {
    id("ycharts.android.application")
    id("ycharts.android.application.compose")

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
//    implementation(co.ycharts.dependency.YChartDependency.CORE_KTX)
//    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_UI)
//    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_MATERIAL)
//    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_TOOLING_PREVIEW)
//    implementation(co.ycharts.dependency.YChartDependency.RUNTIME_KTX)
//    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_ACTIVITY)
//    testImplementation(co.ycharts.dependency.YChartDependency.JUNIT)
//    androidTestImplementation(co.ycharts.dependency.YChartDependency.TEST_EXTN)
//    androidTestImplementation(co.ycharts.dependency.YChartDependency.ESPRESSO_CORE)
//    androidTestImplementation(co.ycharts.dependency.YChartDependency.COMPOSE_JUNIT)
//    debugImplementation(co.ycharts.dependency.YChartDependency.COMPOSE_UI_TOOLING)
//    debugImplementation(co.ycharts.dependency.YChartDependency.COMPOSE_UI_TEST_MANIFEST)
}
