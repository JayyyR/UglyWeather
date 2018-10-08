package com.joeracosta.uglyweather.network

import com.joeracosta.uglyweather.data.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Joe on 12/26/2017.
 */
interface WeatherAPI {

    @GET("{lat},{lon}?units=us&exclude=minutely,hourly,daily,alerts,flags")
    fun getCurrentConditions(@Path("lat") latitude : String, @Path("lon") longitude : String) : Observable<WeatherResponse>

    @GET("{lat},{lon}?units=us&exclude=minutely,hourly,currently,alerts,flags")
    fun getLaterConditions(@Path("lat") latitude: String, @Path("lon") longitude: String) : Observable<WeatherResponse>
}