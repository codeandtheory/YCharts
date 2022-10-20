buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:1.6.10")
        classpath ("org.jetbrains.dokka:dokka-gradle-plugin:1.6.0")
    }

    tasks {
        register("clean", Delete::class) {
            delete(rootProject.buildDir)
        }
    }
}
