package com.joeracosta.uglyweather.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Joe on 12/25/2017.
 */
data class NowWeather(
        @SerializedName("icon")
        val icon : String? = null,

        @SerializedName("temperature")
        val temperatureInFahrenheit: Float? = null,

        @SerializedName("apparentTemperature")
        val feelsLike : Float? = null,

        @SerializedName("humidity")
        val humidity : Float? = null,

        @SerializedName("summary")
        val summary : String? = null
)


data class WeatherResponse(
        @SerializedName("currently")
        val nowWeather: NowWeather? = null,

        @SerializedName("daily")
        val laterWeather : LaterWeather? = null
)