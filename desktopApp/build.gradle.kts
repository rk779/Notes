import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.ksp)
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(compose.material3)

                implementation(libs.voyager.navigator)

                implementation(projects.core.base)
                implementation(projects.core.common)
                implementation(projects.core.network)
                implementation(projects.ui.design)
                implementation(projects.ui.authentication.login)
                implementation(projects.ui.authentication.navigation)
            }
        }
    }
}

compose {
    desktop {
        application {
            mainClass = "MainKt"
            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "Notes"
                packageVersion = "1.0.0"
            }
        }
    }
}

dependencies {
    add("kspJvm", libs.kotlinInject.compiler)
}
