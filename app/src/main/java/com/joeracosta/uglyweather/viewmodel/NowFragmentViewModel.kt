package com.joeracosta.uglyweather.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.model.NowWeather
import com.joeracosta.uglyweather.network.weatherAPI
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