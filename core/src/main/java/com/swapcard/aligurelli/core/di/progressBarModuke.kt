package com.swapcard.aligurelli.core.di

import com.swapcards.common.view.ProgressBarDialog
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val progressbarModule = module {
    single { ProgressBarDialog(androidContext()) }
}