package com.joeracosta.uglyweather.network

import com.joeracosta.uglyweather.App
import com.joeracosta.uglyweather.R
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Joe on 12/26/2017.
 */

val BASE_URL = "https://api.darksky.net/forecast/"

private val retrofit by lazy {
    Retrofit.Builder()
            .baseUrl(BASE_URL + App.appResources.getString(R.string.api_key))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

val weatherAPI by lazy {
    retrofit.create(WeatherAPI::class.java)
}