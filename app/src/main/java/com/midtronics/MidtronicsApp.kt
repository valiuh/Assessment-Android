package com.midtronics

import android.app.Application
import com.midtronics.di.dataModule
import com.midtronics.di.domainModule
import com.midtronics.di.viewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MidtronicsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin() {
            androidContext(this@MidtronicsApp)
            modules(
                dataModule,
                domainModule,
                viewModelModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}