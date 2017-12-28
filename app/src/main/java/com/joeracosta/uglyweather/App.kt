package com.joeracosta.uglyweather

import android.app.Application
import android.content.res.Resources
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Joe on 12/26/2017.
 */
class App : Application() {

    companion object {
        lateinit var appResources: Resources
            private set
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
        appResources = resources
    }
}