package com.frensky.porto

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class PortoApplication :
    Application(),
    DefaultLifecycleObserver,
    Configuration.Provider {
    companion object {
        @get:Synchronized
        lateinit var mInstance: PortoApplication
            private set

        fun getContext(): Context = mInstance.applicationContext
    }


    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Synchronized
    fun getInstance(): PortoApplication = mInstance

    fun getContext(): Context = mInstance.applicationContext

    override val workManagerConfiguration: Configuration
        get() =
            Configuration
                .Builder()
                .setWorkerFactory(workerFactory)
                .build()


    override fun onCreate() {
        super<Application>.onCreate()
        mInstance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        WorkManager.initialize(this, workManagerConfiguration)
    }

}