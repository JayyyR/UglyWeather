package com.joeracosta.uglyweather.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Joe on 12/28/2017.
 */
data class LaterWeather(

        @SerializedName("summary")
        val summary : String? = null,

        @SerializedName("data")
        val data : List<LaterWeatherDay>? = null

)

data class LaterWeatherDay(

        @SerializedName("summary")
        val summary : String? = null,

        @SerializedName("time")
        val time : Long? = null,

        @SerializedName("icon")
        val icon : String? = null,

        @SerializedName("temperatureHigh")
        val high : Float? = null,

        @SerializedName("temperatureLow")
        val low : Float? = null

)