plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

//fun getApiKey(propertyKey: String): String {
//    val localProperties = gradleLocalProperties(rootDir, providers) // providers 명시적 전달
//    return localProperties.getProperty(propertyKey, "error no site here")
//}

android {
    namespace = "com.example.frontcapstone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.frontcapstone"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

//        buildConfigField(
//            "String",
//            "API_KEY",
//            getApiKey(API_BASE_URL)
//        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    val nav_version = "2.8.3"
    //navigation
    implementation(libs.androidx.navigation.compose)

    //icon extension
    implementation(libs.androidx.material.icons.extended)

    //image "io.coil-kt.coil3:coil-compose:3.0.1" // "io.coil-kt.coil3:coil-network-okhttp:3.0.1"
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)


    implementation(libs.play.services.auth) // For Google Sign-In
    implementation(libs.firebase.auth.ktx) // For Firebase Authentication
    implementation(libs.androidx.activity.ktx) // For Activity Result API

    // Retrofit
    implementation(libs.retrofit) //"com.squareup.retrofit2:retrofit:2.9.0"
// Retrofit with Scalar Converter
    implementation(libs.converter.scalars) //"com.squareup.retrofit2:converter-scalars:2.9.0"
    implementation(libs.gson) // "com.google.code.gson:gson:2.11.0"
    implementation(libs.converter.gson) //"com.squareup.retrofit2:converter-gson:2.11.0"

}