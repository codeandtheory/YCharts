plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.piechartcontainer"
        minSdk = 26
        targetSdk = 32
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
        kotlinCompilerExtensionVersion = com.ygraph.dependency.Version.KOTLIN_COMPILER_EXT
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":YChartsLib"))
    implementation(com.ygraph.dependency.YChartDependency.CORE_KTX)
    implementation(com.ygraph.dependency.YChartDependency.COMPOSE_UI)
    implementation(com.ygraph.dependency.YChartDependency.COMPOSE_MATERIAL)
    implementation(com.ygraph.dependency.YChartDependency.COMPOSE_TOOLING_PREVIEW)
    implementation(com.ygraph.dependency.YChartDependency.RUNTIME_KTX)
    implementation(com.ygraph.dependency.YChartDependency.COMPOSE_ACTIVITY)
    testImplementation(com.ygraph.dependency.YChartDependency.JUNIT)
    androidTestImplementation(com.ygraph.dependency.YChartDependency.TEST_EXTN)
    androidTestImplementation(com.ygraph.dependency.YChartDependency.ESPRESSO_CORE)
    androidTestImplementation(com.ygraph.dependency.YChartDependency.COMPOSE_JUNIT)
    debugImplementation(com.ygraph.dependency.YChartDependency.COMPOSE_UI_TOOLING)
    debugImplementation(com.ygraph.dependency.YChartDependency.COMPOSE_UI_TEST_MANIFEST)}
