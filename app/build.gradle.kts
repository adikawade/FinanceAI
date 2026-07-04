plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.financeai.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.financeai.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
}
