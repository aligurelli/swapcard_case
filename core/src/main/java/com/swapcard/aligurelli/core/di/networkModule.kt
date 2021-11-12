package com.swapcard.aligurelli.core.di

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val networkModule = module {
    single { provideApolloClient(get()) }
    single { provideOkHttpClient() }
}

fun provideOkHttpClient(): OkHttpClient {

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

}

fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {

    return  ApolloClient.builder().
        serverUrl("https://graphbrainz.herokuapp.com")
        .okHttpClient(okHttpClient)
        .build()
}

