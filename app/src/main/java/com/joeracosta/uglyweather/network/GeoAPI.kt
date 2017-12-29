package com.joeracosta.uglyweather.network

import com.joeracosta.uglyweather.model.GeoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Joe on 12/26/2017.
 */
interface GeoAPI {
    @GET("json")
    fun getLatLong(@Query("key") key : String, @Query("address") zip: String): Observable<GeoResponse>
}
