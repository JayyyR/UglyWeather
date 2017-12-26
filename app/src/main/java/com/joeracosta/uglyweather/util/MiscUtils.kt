package com.joeracosta.uglyweather.util

import android.support.v4.content.res.ResourcesCompat
import com.joeracosta.uglyweather.App

/**
 * Created by Joe on 12/26/2017.
 */

fun grabString(id : Int) : String {
    return App.appResources.getString(id)
}

fun grabColor(id : Int) : Int {
    return ResourcesCompat.getColor(App.appResources, id, null)
}

fun convertToFahrenheit(tempInCelsius: Float) : Int{
    return Math.round((9F/5F) * tempInCelsius + 32)
}

fun decimalToPercentage(decimal : Float) : String {
    return Math.round(decimal*100F).toString() + "%"
}