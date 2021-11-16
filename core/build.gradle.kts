import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField

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

    buildTypes.forEach {
        it.buildConfigStringField("SWAPCARD_API_BASE_URL", "https://graphbrainz.herokuapp.com/")
        it.buildConfigStringField("SWAPCARD_DATABASE_NAME", "bookmarked.artist-db")
        it.buildConfigIntField("SWAPCARD_DATABASE_VERSION", 1)
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
    implementation(project(mapOf("path" to ":commons:views")))

    kapt(AnnotationProcessorsDependencies.ROOM)

}