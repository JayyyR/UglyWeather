package com.joeracosta.uglyweather.viewmodel

import android.databinding.Bindable
import android.widget.CompoundButton
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.network.geoAPI
import com.joeracosta.uglyweather.util.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by Joe on 12/28/2017.
 */
class SettingsFragmentViewModel : SmartViewModel() {

    @Bindable
    var useCurLocation = StoredData.getStoredShouldUseCurLocation()
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var useCelsius = StoredData.getUseCelsius()

    @Bindable
    var zipCode = StoredData.getStoredZip()

    @Bindable
    var savedLocationText = StoredData.getStoredLocationName()
        set (value){
            field = value
            notifyChange()
        }

    @Bindable
    fun getZipForeground() : Int {
        return if (useCurLocation) grabColor(R.color.disabled) else 0
    }

    @Bindable
    fun getZipEnabled() : Boolean {
        return !useCurLocation
    }

    var degreeSwitchListener = CompoundButton.OnCheckedChangeListener { _, isChecked ->
        StoredData.storeUseCelsius(isChecked)
        useCelsius = isChecked
        EventBus.getDefault().post(UseCelsiusEvent(isChecked))
    }


    fun saveZip() {
        if (zipCode.isEmpty()){
            //todo toast enter zip
            return
        }
        geoAPI.getLatLong(grabString(R.string.geocoding_api_key), zipCode)
                .offMain()
                .subscribe({ geoResponse ->

                    if (geoResponse.status != "OK") {
                        //todo error setting zip
                    } else {
                        val lat = geoResponse.results?.get(0)?.geometry?.location?.lat
                        val lon = geoResponse.results?.get(0)?.geometry?.location?.lon
                        val locationName = formatFormattedAddress(geoResponse.results?.get(0)?.formattedAddress)
                        StoredData.storeSavedZip(zipCode)
                        StoredData.storeSavedLat(lat)
                        StoredData.storeSavedLong(lon)
                        StoredData.storeSavedLocationName(locationName)
                        SessionData.updateLocation(lat, lon, locationName)
                        savedLocationText = locationName
                    }

                }, {
                    System.out.print("")
                    //todo error setting zip
                }).addToComposite()
    }
}