package com.swapcard.aligurelli.core.di

import com.swapcard.aligurelli.core.network.repositories.SampleRepo
import org.koin.dsl.module

val repositoryModule = module {
    factory { SampleRepo(get()) }



}