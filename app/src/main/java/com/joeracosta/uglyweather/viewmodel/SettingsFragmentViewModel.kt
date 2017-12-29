package com.joeracosta.uglyweather.viewmodel

import android.databinding.Bindable
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.network.geoAPI
import com.joeracosta.uglyweather.util.*

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
    var zipCode = StoredData.getStoredZip()

    @Bindable
    fun getZipForeground() : Int {
        return if (useCurLocation) grabColor(R.color.disabled) else 0
    }

    @Bindable
    fun getZipEnabled() : Boolean {
        return !useCurLocation
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
                        StoredData.storeSavedZip(zipCode)
                        StoredData.storeSavedLat(geoResponse.results?.get(0)?.geometry?.location?.lat)
                        StoredData.storeSavedLong(geoResponse.results?.get(0)?.geometry?.location?.lon)
                        SessionData.updateLocation(geoResponse.results?.get(0)?.geometry?.location?.lat, geoResponse.results?.get(0)?.geometry?.location?.lon)
                    }

                }, {
                    System.out.print("")
                    //todo error setting zip
                }).addToComposite()
    }
}