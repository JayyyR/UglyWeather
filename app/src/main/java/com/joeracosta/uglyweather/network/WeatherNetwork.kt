package com.joeracosta.uglyweather.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.joeracosta.uglyweather.BuildConfig
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.util.grabString
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



/**
 * Created by Joe on 12/26/2017.
 */

private const val BASE_URL = "https://api.darksky.net/forecast/"

private val debugClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

private val releaseClient = OkHttpClient.Builder()
        .build()

private val retrofit by lazy {
    Retrofit.Builder()
            .baseUrl(BASE_URL + grabString(R.string.api_key) + "/")
            .client(if (BuildConfig.DEBUG) debugClient else releaseClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

val weatherAPI : WeatherAPI by lazy {
    retrofit.create(WeatherAPI::class.java)
}