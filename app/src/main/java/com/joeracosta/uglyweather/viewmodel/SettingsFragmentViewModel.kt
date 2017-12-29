package com.joeracosta.uglyweather.viewmodel

import android.databinding.Bindable
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.network.geoAPI
import com.joeracosta.uglyweather.util.SessionData
import com.joeracosta.uglyweather.util.StoredData
import com.joeracosta.uglyweather.util.grabString
import com.joeracosta.uglyweather.util.offMain

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
        get() {
            return field
        }

    @Bindable
    private var zipCode = ""
        set (value) {
            field = value
            notifyChange()
        }

    fun saveZipCodeLocation() {
        geoAPI.getLatLong(grabString(R.string.geocoding_api_key), "07452")
                .offMain()
                .subscribe({ geoResponse ->

                    if (geoResponse.status != "OK") {
                        //todo error setting zip
                    } else {
                        SessionData.updateLocation(geoResponse.results?.geometry?.location?.lat, geoResponse.results?.geometry?.location?.lon)
                    }

                }, {
                    System.out.print("");
                }).addToComposite()
    }
}