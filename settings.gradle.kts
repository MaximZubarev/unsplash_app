pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Unsplash app"
include(":app")
include(":core")
include(":core:common")
include(":core:domain")
include(":core:ui")
include(":feature")
include(":feature:photo-feed")
include(":feature:search")
include(":feature:profile")
include(":feature:favorites")
include(":feature:photo")
include(":data")
include(":data")
include(":data:photo-api")
include(":data:photo-impl")
include(":core:network-api")
include(":core:network-impl")
