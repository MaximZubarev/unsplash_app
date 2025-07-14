@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.mldz.unsplashapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mldz.unsplashapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 105
        versionName = "1.0.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:network-impl"))
    implementation(project(":core:db-impl"))
    implementation(project(":core:ui"))
    implementation(project(":data:photo-impl"))
    implementation(project(":data:profile-impl"))
    implementation(project(":feature:photo-feed-api"))
    implementation(project(":feature:photo-feed-impl"))
    implementation(project(":feature:favorites-api"))
    implementation(project(":feature:favorites-impl"))
    implementation(project(":feature:profile-api"))
    implementation(project(":feature:profile-impl"))
    implementation(project(":feature:photo-api"))
    implementation(project(":feature:photo-impl"))
    implementation(project(":feature:search-api"))
    implementation(project(":feature:search-impl"))

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.koin.android)
    implementation(libs.koin.annotations)
    implementation(libs.koin.compose)
    ksp(libs.koin.ksp)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.bundles.compose.preview)
}