package com.joeracosta.uglyweather.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.model.LaterWeather
import com.joeracosta.uglyweather.network.weatherAPI
import com.joeracosta.uglyweather.util.LocationUpdatedEvent
import com.joeracosta.uglyweather.util.SessionData
import com.joeracosta.uglyweather.util.offMain
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Joe on 12/28/2017.
 */

class LaterFragmentViewModel : SmartViewModel(){

    init {
        EventBus.getDefault().register(this)
    }

    private var laterWeather: MutableLiveData<LaterWeather>? = null

    fun observeWeather(): MutableLiveData<LaterWeather> {
        if (laterWeather == null) {
            laterWeather = MutableLiveData()
            fetchWeather()
        }
        return laterWeather as MutableLiveData<LaterWeather>
    }

    private fun fetchWeather() {
        if (SessionData.latitude == null || SessionData.longitude == null){
            //todo prompt user to choose a location in the app
            return
        }
        weatherAPI.getLaterConditions(SessionData.latitude!!, SessionData.longitude!!)
                .offMain()
                .subscribe(
                        { response ->
                            laterWeather?.value = response.laterWeather
                            notifyChange()
                        },
                        { error ->
                            System.out.print("") //todo
                        }
                ).addToComposite()
    }

    @Bindable
    fun getWeekSummary() : String {
        return laterWeather?.value?.summary ?: ""
    }

    @Bindable
    val refreshListener = {
        if (laterWeather == null){ //shouldn't happen
            laterWeather = MutableLiveData()
        }
        fetchWeather()
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onLocationUpdated(event : LocationUpdatedEvent){
        fetchWeather()
    }
}
