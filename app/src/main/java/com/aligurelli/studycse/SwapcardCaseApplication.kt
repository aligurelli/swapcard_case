package com.aligurelli.studycse

import android.app.Application
import com.swapcard.aligurelli.core.di.networkModule
import com.swapcard.aligurelli.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SwapcardCaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection(){
        startKoin {
            androidContext(this@SwapcardCaseApplication)
            modules(networkModule, repositoryModule)
        }
    }
}