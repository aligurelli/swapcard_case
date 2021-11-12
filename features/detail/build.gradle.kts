plugins {
    id("commons.android-dynamic-feature")
}

dependencies {
    implementation(project(BuildModules.Features.HOME))

}
android {
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}
