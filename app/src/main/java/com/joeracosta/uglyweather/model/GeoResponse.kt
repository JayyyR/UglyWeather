package com.joeracosta.uglyweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Joe on 12/28/2017.
 */
data class GeoResponse(
        @SerializedName("results")
        val results : GeoResults? = null,

        @SerializedName("status")
        val status : String? = null

)

data class GeoResults(
        @SerializedName("geometry")
        val geometry: GeoGeometry? = null
)

data class GeoGeometry(
        @SerializedName("location")
        val location: GeoLocation? = null
)

data class GeoLocation(
        @SerializedName("lat")
        val lat : String? = null,

        @SerializedName("lng")
        val lon : String? = null
)