
plugins {
    id("commons.android-dynamic-feature")
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


}

dependencies{
    implementation(Dependencies.PAGING)

}