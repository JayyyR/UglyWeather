package com.joeracosta.uglyweather

import android.app.Application
import android.content.res.Resources

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

        appResources = resources
    }
}