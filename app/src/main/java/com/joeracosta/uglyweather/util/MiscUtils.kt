package com.joeracosta.uglyweather.util

import com.joeracosta.uglyweather.App

/**
 * Created by Joe on 12/26/2017.
 */

fun grabString(id : Int) : String {
    return App.appResources.getString(id)
}

fun convertToFahrenheit(tempInCelsius: Float) : Int{
    return Math.round((9F/5F) * tempInCelsius + 32)
}