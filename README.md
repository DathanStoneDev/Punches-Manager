# Punches-Manager

### Android application used to track "Punches" lifespans, doses, locations and active status.

Punches are essential tool sets in the Dietary Supplement industry, as they form the tablets in compression machines. This application allows a user such as a Tooling Manager or Production Manager to perform the following:

:small_blue_diamond: Track the life of a tool set to see if it meets the lifespan. <br />
:small_blue_diamond: Track every product a tool set has created. <br />
:small_blue_diamond: Track total doses a tool set produces and for a specific product. <br />
:small_blue_diamond: Tracks if the tool set is currently running on a machine or is inactive. <br />
:small_blue_diamond: Produces lifespan, dosing and product reports. <br />

## Project Video

https://user-images.githubusercontent.com/73630546/182035894-0e9b66f1-3ff4-47dc-b83a-85894955055c.mp4

## Installation and Setup Instructions

### Installation
:small_blue_diamond: Clone this repository from Android Studio <br />
:small_blue_diamond: Create a virtual device using Android Virtual Device Manager. Min API: 26 - Ensure the virtual device has the Google Play Store. <br />

### Dependencies
:small_blue_diamond: Add these dependencies to the Module build.gradle file.
```
dependencies {

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation "androidx.compose.ui:ui:1.1.1"
    implementation "androidx.compose.material:material:1.1.1"
    implementation "androidx.compose.ui:ui-tooling-preview:1.1.1"
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.4.1'
    implementation 'androidx.activity:activity-compose:1.4.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    androidTestImplementation "androidx.compose.ui:ui-test-junit4:1.1.1"
    debugImplementation "androidx.compose.ui:ui-tooling:1.1.1"
    implementation 'androidx.compose.material:material-icons-extended:1.1.1'

    //Room Dependencies
    def roomVersion = "2.4.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    //Dagger - Hilt
    implementation 'com.google.dagger:hilt-android:2.42'
    kapt 'com.google.dagger:hilt-compiler:2.42'
    kapt "androidx.hilt:hilt-compiler:1.0.0"
    implementation 'androidx.hilt:hilt-navigation-compose:1.0.0'

    //Password hashing algorith with: JBcrypt
    implementation("org.mindrot:jbcrypt:0.4")
}

kapt {
    correctErrorTypes = true
}
```
### Plugins
:small_blue_diamond: Add these plugins to the Module build.gradle file
```
id 'org.jetbrains.kotlin.kapt'
id 'dagger.hilt.android.plugin'
```
