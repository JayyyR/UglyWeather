package com.joeracosta.uglyweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Joe on 12/25/2017.
 */
data class NowWeather(

        @SerializedName("icon")
        val icon : String? = null,

        @SerializedName("temperature")
        val tempInCelsius: Float? = null,

        @SerializedName("apparentTemperature")
        val feelsLike : Float? = null,

        @SerializedName("humidity")
        val humidity : Float? = null,

        @SerializedName("summary")
        val summary : String? = null

)