plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.dokka")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
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

    implementation(co.ycharts.dependency.YChartDependency.CORE_KTX)
    implementation(co.ycharts.dependency.YChartDependency.APPCOMPAT)
    implementation(co.ycharts.dependency.YChartDependency.MATERIAL)
    implementation (co.ycharts.dependency.YChartDependency.MATERIAL_3)
    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_UI)
    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_ACTIVITY)
    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_MATERIAL)
    implementation(co.ycharts.dependency.YChartDependency.COMPOSE_TOOLING_PREVIEW)
    testImplementation(co.ycharts.dependency.YChartDependency.JUNIT)
    testImplementation(co.ycharts.dependency.YChartDependency.MOCKK)
    androidTestImplementation(co.ycharts.dependency.YChartDependency.COMPOSE_JUNIT)
    debugImplementation(co.ycharts.dependency.YChartDependency.COMPOSE_UI_TEST_MANIFEST)
    androidTestImplementation(co.ycharts.dependency.YChartDependency.TEST_EXTN)
    androidTestImplementation(co.ycharts.dependency.YChartDependency.ESPRESSO_CORE)
}
