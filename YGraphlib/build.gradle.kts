import com.ygraph.dependency.YChartDependency
import com.ygraph.dependency.Version

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.KOTLIN_COMPILER_EXT
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(YChartDependency.CORE_KTX)
    implementation(YChartDependency.APPCOMPAT)
    implementation(YChartDependency.MATERIAL)
    implementation(YChartDependency.COMPOSE_UI)
    implementation(YChartDependency.COMPOSE_MATERIAL)
    implementation(YChartDependency.COMPOSE_TOOLING_PREVIEW)
    testImplementation(YChartDependency.JUNIT)
    androidTestImplementation(YChartDependency.TEST_EXTN)
    androidTestImplementation(YChartDependency.ESPRESSO_CORE)
}
