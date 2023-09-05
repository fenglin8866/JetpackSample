plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
  //  id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.jetpack.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jetpack.sample"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/*.jar"
        }
    }
}

dependencies {

    implementation(project(mapOf("path" to ":libpaging")))

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    val paging_version = "3.2.0"

    implementation("androidx.paging:paging-runtime-ktx:$paging_version")

    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common-ktx:$paging_version")

    // optional - RxJava2 support
    implementation("androidx.paging:paging-rxjava2-ktx:$paging_version")

    // optional - RxJava3 support
    implementation("androidx.paging:paging-rxjava3:$paging_version")

    // optional - Guava ListenableFuture support
    implementation("androidx.paging:paging-guava:$paging_version")

    // optional - Jetpack Compose integration
    implementation("androidx.paging:paging-compose:$paging_version")

    val room_version = "2.5.2"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
  //  kapt("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$room_version")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$room_version")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")


    val androidx_navigation = "2.7.1"
    implementation("androidx.navigation:navigation-ui-ktx:$androidx_navigation")
    implementation("androidx.navigation:navigation-ui-ktx:$androidx_navigation")
    implementation("androidx.navigation:navigation-common:$androidx_navigation")
    implementation("androidx.navigation:navigation-common-ktx:$androidx_navigation")
    implementation("androidx.navigation:navigation-runtime-ktx:$androidx_navigation")
    implementation("androidx.navigation:navigation-compose:$androidx_navigation")
    implementation("androidx.navigation:navigation-fragment-ktx:$androidx_navigation")

    val glide_version = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glide_version")
    //ksp("com.github.bumptech.glide:compiler:$glide_version")
    // implementation("com.github.bumptech.glide:ksp:$glide_version")


    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.retrofit2:retrofit-mock:$retrofit_version")

    val okhttp_logging_interceptor = "4.11.0"
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_logging_interceptor")

    val androidx_lifecycle = "2.6.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$androidx_lifecycle")


}


