
plugins {
    id("commons.android-library")
    id("com.apollographql.apollo").version("2.5.9")

}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    apollo {
        generateKotlinModels.set(true) // or false for Java models
    }
}

dependencies {


    api(Dependencies.KOIN)
    api(Dependencies.KOIN_COMPOSE)

    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.PAGING)

    kapt(AnnotationProcessorsDependencies.ROOM)

}