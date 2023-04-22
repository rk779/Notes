plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "in.rk585.notes.android"

    defaultConfig {
        applicationId = "in.rk585.notes.android"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.3.1")
    implementation("androidx.compose.ui:ui-tooling:1.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.1")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation(compose.material3)

    // Dependency Injection
    ksp(libs.kotlinInject.compiler)

    // Navigation
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)

    implementation(projects.shared)
    implementation(projects.core.base)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.ui.authentication.login)
    implementation(projects.ui.authentication.navigation)
    implementation(projects.ui.design)
}
