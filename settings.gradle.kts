@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":androidApp")
include(":desktopApp")
include(":iosComposeApp")
include(":core:base")
include(":core:data")
include(":core:domain")
include(":shared")
include(":ui:authentication:login")
include(":ui:design")
include(":ui:navigation")

rootProject.name = "Notes"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
