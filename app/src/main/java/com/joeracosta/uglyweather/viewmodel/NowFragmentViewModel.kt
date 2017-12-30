package com.joeracosta.uglyweather.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.crashlytics.android.Crashlytics
import com.joeracosta.uglyweather.BR
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.model.NowWeather
import com.joeracosta.uglyweather.network.weatherAPI
import com.joeracosta.uglyweather.util.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Joe on 12/26/2017.
 */

class NowFragmentViewModel : SmartViewModel() {

    init {
        EventBus.getDefault().register(this)
    }

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
            return grabAnimationDrawableResourceFromIcon(it)
        }
        return 0
    }

    @Bindable
    fun getTemperature() : String {
        return if (StoredData.getUseCelsius())
            formatWeatherInCelsius(nowWeather?.value?.temperatureInFahrenheit)
        else
            formatWeatherInFahrenheit(nowWeather?.value?.temperatureInFahrenheit)
    }

    @Bindable
    fun getSummary() : String {
        return nowWeather?.value?.summary ?: ""
    }

    @Bindable
    fun getFeelsLike() : String {
        return if (StoredData.getUseCelsius())
            grabString(R.string.feels_like_label) + formatWeatherInCelsius(nowWeather?.value?.feelsLike)
        else
            grabString(R.string.feels_like_label) + formatWeatherInFahrenheit(nowWeather?.value?.feelsLike)
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
    fun getLocationText() : String {
        return SessionData.locationName ?: ""
    }

    @Bindable
    val refreshListener = {
        if (nowWeather == null){ //shouldn't happen
            nowWeather = MutableLiveData()
        }
        fetchWeather()
    }

    private fun fetchWeather() {
        if (SessionData.latitude == null || SessionData.longitude == null){
            if (!StoredData.getStoredShouldUseCurLocation()) { //if we're not waiting for the cur loc
                alertUserSubject.onNext(R.string.set_location_prompt)
            }
            return
        }
        weatherAPI.getCurrentConditions(SessionData.latitude!!, SessionData.longitude!!)
                .offMain()
                .subscribe(
                { response ->
                    nowWeather?.value = response.nowWeather
                    notifyChange()
                },
                { error ->
                    Crashlytics.logException(error)
                    alertUserSubject.onNext(R.string.error_server)
                }
        ).addToComposite()
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onLocationUpdated(event : LocationUpdatedEvent){
        fetchWeather()
    }

    @Subscribe
    fun onCelsiusPreferenceSwitched(event : UseCelsiusEvent){
        notifyPropertyChanged(BR.temperature)
        notifyPropertyChanged(BR.feelsLike)
    }
}