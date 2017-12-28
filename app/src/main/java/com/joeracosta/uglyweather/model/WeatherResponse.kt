package com.joeracosta.uglyweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Joe on 12/25/2017.
 */

data class WeatherResponse(
        @SerializedName("currently")
        val nowWeather: NowWeather? = null,

        @SerializedName("daily")
        val laterWeather : LaterWeather? = null
)