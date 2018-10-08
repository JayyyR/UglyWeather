package com.joeracosta.uglyweather.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Joe on 12/28/2017.
 */
data class GeoResponse(
        @SerializedName("results")
        val results : List<GeoResults>? = null,

        @SerializedName("status")
        val status : String? = null

)

data class GeoResults(
        @SerializedName("geometry")
        val geometry: GeoGeometry? = null,

        @SerializedName("formatted_address")
        val formattedAddress : String? = null
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