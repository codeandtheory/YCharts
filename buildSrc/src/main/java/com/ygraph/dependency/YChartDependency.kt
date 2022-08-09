package com.ygraph.dependency

object YChartDependency {
    val CORE_KTX by lazy { "androidx.core:core-ktx:${Version.CORE_KTX}" }
    val APPCOMPAT by lazy { "androidx.appcompat:appcompat:${Version.APPCOMPAT}" }
    val MATERIAL by lazy { "com.google.android.material:material:${Version.MATERIAL}" }
    val MATERIAL_3 by lazy{ "androidx.compose.material3:material3:${Version.MATERIAL_3}"}
    val JUNIT by lazy { "junit:junit:${Version.JUNIT}" }
    val TEST_EXTN by lazy { "androidx.test.ext:junit:${Version.TEST_EXTN}" }
    val ESPRESSO_CORE by lazy { "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}" }
    val COMPOSE_UI by lazy { "androidx.compose.ui:ui:${Version.COMPOSE_UI}" }
    val COMPOSE_MATERIAL by lazy { "androidx.compose.material:material:${Version.COMPOSE_UI}" }
    val COMPOSE_TOOLING_PREVIEW by lazy { "androidx.compose.ui:ui-tooling-preview:${Version.COMPOSE_UI}" }
    val COMPOSE_ACTIVITY by lazy { "androidx.activity:activity-compose:${Version.COMPOSE_ACTIVITY}" }
    val COMPOSE_JUNIT by lazy { "androidx.compose.ui:ui-test-junit4:${Version.COMPOSE_UI}" }
    val COMPOSE_UI_TOOLING by lazy { "androidx.compose.ui:ui-tooling:${Version.COMPOSE_UI}" }
    val COMPOSE_UI_TEST_MANIFEST by lazy { "androidx.compose.ui:ui-test-manifest:${Version.COMPOSE_UI}" }
    val RUNTIME_KTX by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Version.RUNTIME_KTX}" }
}
