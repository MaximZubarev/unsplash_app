@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.mldz.data.photo_impl"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
}

dependencies {
    implementation(project(":data:photo-api"))
    implementation(project(":core:common"))
    implementation(project(":core:network-api"))
    implementation(project(":core:db-api"))

    implementation(libs.kotlin.coroutines)
    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp)
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.paging)
}