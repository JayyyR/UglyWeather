package com.joeracosta.uglyweather.util

import android.support.v4.content.res.ResourcesCompat
import com.joeracosta.uglyweather.App
import com.joeracosta.uglyweather.R

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

fun grabDrawableResourceFromIcon(iconName : String) : Int{
    return when (iconName) {
        "clear-day" -> R.drawable.clear_day_animation
        "clear-night" -> R.drawable.clear_night_animation
        else -> R.drawable.clear_day_animation //todo better default
    }
}