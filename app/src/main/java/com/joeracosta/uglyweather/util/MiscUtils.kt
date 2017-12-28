package com.joeracosta.uglyweather.util

import android.support.v4.content.res.ResourcesCompat
import com.joeracosta.uglyweather.App
import com.joeracosta.uglyweather.R
import java.util.*



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

fun grabAnimationDrawableResourceFromIcon(iconName : String) : Int{
    return when (iconName) {
        "clear-day" -> R.drawable.clear_day_animation
        "clear-night" -> R.drawable.clear_night_animation
        "rain" -> R.drawable.rain_animation
        "snow" -> R.drawable.snow_animation
        "wind" -> R.drawable.wind_animation
        "sleet" -> R.drawable.sleet_animation
        "fog" -> R.drawable.fog_animation
        "cloudy" -> R.drawable.cloudy_animation
        "partly-cloudy-day" -> R.drawable.partly_cloudy_day_animation
        "partly-cloudy-night" -> R.drawable.partly_cloudy_night_animation
        else -> R.drawable.clear_day_animation //todo better default
    }
}

fun isToday(date: Date): Boolean {
    return isSameDay(date, Calendar.getInstance().time)
}


fun isSameDay(date1: Date, date2: Date): Boolean {
    val cal1 = Calendar.getInstance()
    cal1.time = date1
    val cal2 = Calendar.getInstance()
    cal2.time = date2
    return isSameDay(cal1, cal2)
}

/**
 *
 * Checks if two calendars represent the same day ignoring time.
 * @param cal1  the first calendar, not altered, not null
 * @param cal2  the second calendar, not altered, not null
 * @return true if they represent the same day
 */
fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
    return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}