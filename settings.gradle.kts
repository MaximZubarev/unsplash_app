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
include(":feature:photo-feed-impl")
include(":feature:search-api")
include(":feature:profile-api")
include(":feature:favorites-api")
include(":feature:photo-api")
include(":data")
include(":data")
include(":data:photo-api")
include(":data:photo-impl")
include(":core:network-api")
include(":core:network-impl")
include(":feature:photo-feed-api")
include(":feature:favorites-impl")
include(":feature:profile-impl")
include(":feature:photo-impl")
include(":feature:search-impl")
