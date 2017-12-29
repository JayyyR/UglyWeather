package com.joeracosta.uglyweather.viewmodel

import android.annotation.SuppressLint
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.model.LaterWeatherDay
import com.joeracosta.uglyweather.util.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Joe on 12/28/2017.
 */
class LaterWeatherCardViewModel : BaseObservable() {

    var laterWeatherDay : LaterWeatherDay? = null
        set(value) {
            field = value
            notifyChange()
        }

    @SuppressLint("SimpleDateFormat")
    @Bindable
    fun getDate() : String {
        laterWeatherDay?.time?.let {
            val date = Date(it* 1000)
            if (isToday(date)){
                return grabString(R.string.today)
            }

            return SimpleDateFormat(grabString(R.string.later_date_format)).format(date)
        }
        return ""
    }

    @Bindable
    fun getSummary() : String {
        return laterWeatherDay?.summary ?: ""
    }

    @Bindable
    fun getIconResource() : Int {
        laterWeatherDay?.icon?.let {
            return grabIconDrawableResourceFromIcon(it)
        }
        return 0
    }

    @Bindable
    fun getHigh() : String {
        return if (StoredData.getUseCelsius())
            formatWeatherInCelsius(laterWeatherDay?.high)
        else
            formatWeatherInFahrenheit(laterWeatherDay?.high)

    }

    @Bindable
    fun getLow() : String {
        return if (StoredData.getUseCelsius())
            formatWeatherInCelsius(laterWeatherDay?.low)
        else
            formatWeatherInFahrenheit(laterWeatherDay?.low)

    }

}