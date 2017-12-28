package com.joeracosta.uglyweather.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.joeracosta.uglyweather.model.LaterWeatherDay

/**
 * Created by Joe on 12/28/2017.
 */
class LaterWeatherCardViewModel : BaseObservable() {

    var laterWeatherDay : LaterWeatherDay? = null
        set(value) {
            field = value
            notifyChange()
        }


    @Bindable
    fun getSummary() : String {
        return laterWeatherDay?.summary ?: ""
    }
}