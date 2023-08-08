@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("ycharts.android.library")
    id("ycharts.android.library.compose")
    id("ycharts.android.test")
    id("maven-publish")
    id("signing")
    alias(versionCatalogLibs.plugins.dokka)
}

android {
    compileSdk = 33
    namespace = "co.yml.charts.components"
    defaultConfig {
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
}
val dokkaOutputDir = "$buildDir/dokka"

tasks.dokkaHtml {
    outputDirectory.set(file(dokkaOutputDir))
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}
val javadocJar = tasks.register<Jar>("javadocJar") {
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaOutputDir)
}
publishing {
    repositories {
        maven {
            name = "YCharts"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
            credentials {
                username = project.findProperty("mavenCentralUsername")?.toString() ?: System.getenv("MAVEN_USERNAME")
                password = project.findProperty("mavenCentralPassword")?.toString() ?: System.getenv("MAVEN_PASSWORD")
            }
        }
    }
    publications {
        register<MavenPublication>("release") {
            groupId = "co.yml"
            artifactId = "ycharts"
            version = "2.1.0"
            artifact(javadocJar)
            pom {
                name.set("YCharts")
                description.set("YCharts is a light and extensible chart library for Jetpack Compose system.")
                url.set("https://github.com/yml-org/YCharts")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("dkk009")
                        name.set("Deepak KK")
                        url.set("https://github.com/dkk009")
                    }
                    developer {
                        id.set("preetham1316")
                        name.set("Preetham Ivan Dsouza")
                        url.set("https://github.com/preetham1316")
                    }
                    developer {
                        id.set("kikoso")
                        name.set("Enrique López Mañas")
                        url.set("https://github.com/kikoso")
                    }
                }
                scm {
                    url.set("https://github.com/yml-org/YCharts")
                    connection.set("scm:git:git://github.com/yml-org/YCharts.git")
                    developerConnection.set("scm:git:ssh://git@github.com:yml-org/YCharts.git")
                }
            }
        }
    }
}


signing {
    useInMemoryPgpKeys(
        project.findProperty("signing.keyId")?.toString() ?: System.getenv("SIGNINGKEY"),
        project.findProperty("signing.InMemoryKey")?.toString() ?: System.getenv("MEMORY_KEY"),
        project.findProperty("signing.password")?.toString()?:System.getenv("SIGNINGPASSWORD")
    )
    sign(publishing.publications)
}
