package com.joeracosta.uglyweather.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.model.NowWeather
import com.joeracosta.uglyweather.network.weatherAPI

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


    private fun fetchWeather() {
        weatherAPI.getForecast("42.3601", "-71.0589").subscribe(
                { response ->
                    nowWeather?.value = response.nowWeather
                    notifyChange()
                },
                { error ->
                    //todo error
                }
        ).addToComposite()

    }
}