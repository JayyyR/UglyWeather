package com.joeracosta.uglyweather.network

import com.joeracosta.uglyweather.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Joe on 12/26/2017.
 */
interface WeatherAPI {

    @GET("{lat},{lon}")
    fun getForecast(@Path("lat") latitude : String, @Path("lon") longitude : String) : Observable<WeatherResponse>
}