import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.buildconfig)
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.supabase.goTrue)

                implementation(projects.core.base)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
    }
}

android {
    namespace = "in.rk585.notes.core.network"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

buildConfig {
    packageName.set("in.rk585.notes")

    buildConfigField("String", "API_URL", "\"${propOrDef("SUPABASE_API_URL", "")}\"")
    buildConfigField("String", "API_KEY", "\"${propOrDef("SUPABASE_API_KEY", "")}\"")
}

fun <T : Any> propOrDef(propertyName: String, defaultValue: T): T {
    @Suppress("UNCHECKED_CAST")
    val propertyValue = project.properties[propertyName] as T?
    val localPropertyValue = gradleLocalProperties(rootDir).getProperty(propertyName) as T?
    return propertyValue ?: localPropertyValue ?: defaultValue
}
