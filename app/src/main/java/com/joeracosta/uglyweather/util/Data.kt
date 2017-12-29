package com.joeracosta.uglyweather.util

import android.annotation.SuppressLint
import com.joeracosta.uglyweather.App
import com.joeracosta.uglyweather.R
import org.greenrobot.eventbus.EventBus

/**
 * Created by Joe on 12/27/2017.
 */

object Data {
    fun useSavedLocation(){
        StoredData.storeShouldUseCurLocation(false)
        SessionData.updateLocation(StoredData.getStoredLat(), StoredData.getStoredLon())
    }
}

object SessionData {
    var longitude: String? = null
        private set
    var latitude: String? = null
        private set

    fun updateLocation(newLat: String?, newLon: String?) {
        val locationUpdated = (newLat != latitude || newLon != longitude)
        latitude = newLat
        longitude = newLon

        if (locationUpdated && newLat != null && newLon != null) {
            EventBus.getDefault().post(LocationUpdatedEvent())
        }
    }
}

object StoredData {

    fun getStoredLat() : String? {
        return App.sharedPreferences.getString(grabString(R.string.lat_storage), null)
    }
    fun getStoredLon() : String? {
        return App.sharedPreferences.getString(grabString(R.string.lon_storage), null)
    }
    fun getStoredZip() : String? {
        return App.sharedPreferences.getString(grabString(R.string.zip_storage), null)
    }
    @SuppressLint("ApplySharedPref")
    fun getStoredShouldUseCurLocation() : Boolean {
        val returnVal = App.sharedPreferences.getBoolean(grabString(R.string.location_preference_storage), true)
        if (!App.sharedPreferences.contains(grabString(R.string.location_preference_storage))){
            //default should be true and use commit to be synchronous
            App.sharedPreferences.edit().putBoolean(grabString(R.string.location_preference_storage), true).commit()
        }
        return returnVal
    }
    fun storeShouldUseCurLocation(shouldUserCurLocation : Boolean){
        App.sharedPreferences.edit().putBoolean(grabString(R.string.location_preference_storage), shouldUserCurLocation).apply()
    }
}