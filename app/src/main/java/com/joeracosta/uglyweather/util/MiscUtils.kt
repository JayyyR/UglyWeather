package com.joeracosta.uglyweather.util

import android.support.v4.content.res.ResourcesCompat
import com.joeracosta.uglyweather.App
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.data.DegreeType
import java.util.*
import java.util.regex.Pattern


/**
 * Created by Joe on 12/26/2017.
 */

object WeatherFormatter {
    fun formatWeather(weatherInFahrenheit: Float?): String {
        val degreeType = StoredData.getDegreeType()
        weatherInFahrenheit?.let {
            return when (degreeType) {
                DegreeType.FAHRENHEIT -> formatWeatherInFahrenheit(weatherInFahrenheit)
                DegreeType.CELSIUS -> formatWeatherInCelsius(weatherInFahrenheit)
                DegreeType.WEISHAUS -> formatWeatherInWeishaus(weatherInFahrenheit)
            }
        }
        return ""
    }


    private fun formatWeatherInFahrenheit(weatherInFahrenheit: Float?): String {
        weatherInFahrenheit?.let {
            return Math.round(it).toString() + grabString(R.string.degree_symbol) + "F"
        }
        return ""
    }

    private fun formatWeatherInCelsius(weatherInFahrenheit: Float?) : String {
        weatherInFahrenheit?.let {
            return convertToCelsius(it).toString() + grabString(R.string.degree_symbol) + "C"
        }
        return ""
    }

    private fun convertToCelsius(tempInFahrenheit: Float): Int {
        return Math.round((5F / 9F) * (tempInFahrenheit - 32))
    }

    private fun convertToWeishaus(tempInFahrenheit: Float) : Int {
        //(°F - 32) / 0.666 = °W
        return Math.round((tempInFahrenheit - 32) / 0.666F)
    }

    private fun formatWeatherInWeishaus(weatherInFahrenheit: Float?) : String {
        weatherInFahrenheit?.let {
            return convertToWeishaus(it).toString() + grabString(R.string.degree_symbol) + "W"
        }
        return ""
    }

}
fun grabString(id: Int): String {
    return App.appResources.getString(id)
}

fun grabColor(id: Int): Int {
    return ResourcesCompat.getColor(App.appResources, id, null)
}

fun decimalToPercentage(decimal: Float): String {
    return Math.round(decimal * 100F).toString() + "%"
}

fun formatFormattedAddress(formattedAddress : String?) : String {
    if (formattedAddress == null){
        return ""
    }
    val matcher = Pattern.compile("\\d+").matcher(formattedAddress)
    matcher.find()
    var indexOfFirstDigit = -1
    try {
         indexOfFirstDigit = formattedAddress.indexOf(matcher.group())
    } catch (e : IllegalStateException){
        return formattedAddress
    }
    if (indexOfFirstDigit != -1) {
        return formattedAddress.substring(0, indexOfFirstDigit)
    } else{
        return formattedAddress
    }
}

fun grabAnimationDrawableResourceFromIcon(iconName: String): Int {
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

fun grabIconDrawableResourceFromIcon(iconName: String): Int {
    return when (iconName) {
        "clear-day" -> R.drawable.clear_day_icon
        "clear-night" -> R.drawable.clear_night_icon
        "rain" -> R.drawable.rain_icon
        "snow" -> R.drawable.snow_icon
        "wind" -> R.drawable.wind_icon
        "sleet" -> R.drawable.sleet_icon
        "fog" -> R.drawable.fog_icon
        "cloudy" -> R.drawable.cloudy_icon
        "partly-cloudy-day" -> R.drawable.partly_cloudy_day_icon
        "partly-cloudy-night" -> R.drawable.partly_cloudy_night_icon
        else -> R.drawable.clear_day_icon //todo better default
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