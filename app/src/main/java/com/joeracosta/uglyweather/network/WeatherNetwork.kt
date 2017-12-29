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

private const val WEATHER_BASE_URL = "https://api.darksky.net/forecast/"
private const val GEOCODING_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/"

private val debugClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

private val releaseClient = OkHttpClient.Builder()
        .build()

private val weatherRetrofit by lazy {
    Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL + grabString(R.string.weather_api_key) + "/")
            .client(if (BuildConfig.DEBUG) debugClient else releaseClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

private val geoRetrofit by lazy {
    Retrofit.Builder()
            .baseUrl(GEOCODING_BASE_URL)
            .client(if (BuildConfig.DEBUG) debugClient else releaseClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

val weatherAPI : WeatherAPI by lazy {
    weatherRetrofit.create(WeatherAPI::class.java)
}

val geoAPI : GeoAPI by lazy {
    geoRetrofit.create(GeoAPI::class.java)
}