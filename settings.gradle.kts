@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":androidApp")
include(":desktopApp")
include(":iosComposeApp")
include(":core:base")
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:network")
include(":ui:authentication:login")
include(":ui:authentication:navigation")
include(":ui:authentication:signup")
include(":ui:design")
include(":ui:navigation")
include(":ui:splash")

rootProject.name = "Notes"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
