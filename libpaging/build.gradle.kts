plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.jetpack.lib"
    compileSdk = 34

    defaultConfig {
        minSdk = 29
        /* targetSdk = 33
         versionCode = 1
         versionName = "1.0"*/

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val paging_version = "3.2.0"

    implementation("androidx.paging:paging-runtime-ktx:$paging_version")

    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common-ktx:$paging_version")

    val room_version = "2.5.2"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
//       kapt("androidx.room:room-compiler:$room_version")
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

    val glide_version = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glide_version")
    //implementation("com.github.bumptech.glide:ksp:$glide_version")
    kapt("com.github.bumptech.glide:compiler:$glide_version")


    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.retrofit2:retrofit-mock:$retrofit_version")

    val androidx_lifecycle = "2.6.2"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$androidx_lifecycle")

    implementation("androidx.fragment:fragment-ktx:1.6.1")

}