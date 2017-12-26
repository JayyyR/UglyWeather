package com.joeracosta.uglyweather.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.model.NowWeather
import com.joeracosta.uglyweather.network.weatherAPI
import com.joeracosta.uglyweather.util.decimalToPercentage
import com.joeracosta.uglyweather.util.grabDrawableResourceFromIcon
import com.joeracosta.uglyweather.util.grabString
import com.joeracosta.uglyweather.util.offMain

/**
 * Created by Joe on 12/26/2017.
 */

class NowFragmentViewModel : SmartViewModel() {

    private var nowWeather: MutableLiveData<NowWeather>? = null

    fun observeWeather(): MutableLiveData<NowWeather> {
        if (nowWeather == null) {
            nowWeather = MutableLiveData()
            fetchWeather()
        }
        return nowWeather as MutableLiveData<NowWeather>
    }

    @Bindable
    fun getImageResource () : Int {
        nowWeather?.value?.icon?.let {
            return grabDrawableResourceFromIcon(it)
        }

        return 0
    }

    @Bindable
    fun getTemperature() : String {
        nowWeather?.value?.tempInCelsius?.let {
            //todo celsius or fahrenheit
            return Math.round(it).toString() + grabString(R.string.degree_symbol) + "F"
        }

        return ""
    }

    @Bindable
    fun getSummary() : String {
        return nowWeather?.value?.summary ?: ""
    }

    @Bindable
    fun getFeelsLike() : String {
        var feelsLike = grabString(R.string.feels_like_label)

        nowWeather?.value?.feelsLike?.let {
            feelsLike += Math.round(it).toString() + grabString(R.string.degree_symbol) + "F"
        }

        return feelsLike
    }

    @Bindable
    fun getHumidity() : String {
        var humidity =  grabString(R.string.humidity_label)
        nowWeather?.value?.humidity?.let {
           humidity += it.decimalToPercentage()
        }

        return humidity
    }

    @Bindable
    val refreshListener = {
        if (nowWeather == null){ //shouldn't happen
            nowWeather = MutableLiveData()
        }
        fetchWeather()
    }

    private fun fetchWeather() {
        weatherAPI.getCurrentConditions("42.3601", "-71.0589")
                .offMain()
                .subscribe(
                { response ->
                    nowWeather?.value = response.nowWeather
                    notifyChange()
                },
                { error ->
                    System.out.print("") //todo
                }
        ).addToComposite()

    }
}