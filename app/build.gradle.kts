plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "co.yml.ycharts.app"
        minSdk = 26
        targetSdk = 33
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = co.ycharts.dependency.Version.KOTLIN_COMPILER_EXT
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":KMMYCharts")))
    implementation(project(mapOf("path" to ":YChartsLib")))
    implementation(co.ycharts.dependency.YChartDependency.CORE_KTX)
    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_UI)
    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_MATERIAL)
    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_TOOLING_PREVIEW)
    implementation(co.ycharts.dependency.YChartDependency.RUNTIME_KTX)
    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_ACTIVITY)
    testImplementation(co.ycharts.dependency.YChartDependency.JUNIT)
    androidTestImplementation(co.ycharts.dependency.YChartDependency.TEST_EXTN)
    androidTestImplementation(co.ycharts.dependency.YChartDependency.ESPRESSO_CORE)
    androidTestImplementation(co.ycharts.dependency.YChartDependency.COMPOSE_JUNIT)
    debugImplementation(co.ycharts.dependency.YChartDependency.COMPOSE_UI_TOOLING)
    debugImplementation(co.ycharts.dependency.YChartDependency.COMPOSE_UI_TEST_MANIFEST)
}
