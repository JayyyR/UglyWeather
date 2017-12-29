package com.joeracosta.uglyweather.util

import android.annotation.SuppressLint
import com.joeracosta.uglyweather.App
import com.joeracosta.uglyweather.R
import org.greenrobot.eventbus.EventBus

/**
 * Created by Joe on 12/27/2017.
 */

object Data {
    fun useSavedLocation() {
        StoredData.storeShouldUseCurLocation(false)
        SessionData.updateLocation(StoredData.getStoredLat(), StoredData.getStoredLon(), StoredData.getStoredLocationName())
    }
}

object SessionData {
    var longitude: String? = null
        private set
    var latitude: String? = null
        private set
    var locationName : String? = null

    fun updateLocation(newLat: String?, newLon: String?, newLocationName : String?) {
        val locationUpdated = (newLat != latitude || newLon != longitude)
        latitude = newLat
        longitude = newLon
        locationName = newLocationName

        if (locationUpdated && newLat != null && newLon != null) {
            EventBus.getDefault().post(LocationUpdatedEvent())
        }
    }
}

object StoredData {

    fun getStoredLat(): String? {
        return App.sharedPreferences.getString(grabString(R.string.lat_storage), null)
    }

    fun getStoredLon(): String? {
        return App.sharedPreferences.getString(grabString(R.string.lon_storage), null)
    }

    fun getStoredZip(): String {
        return App.sharedPreferences.getString(grabString(R.string.zip_storage), "")
    }

    fun getStoredLocationName() : String {
        return App.sharedPreferences.getString(grabString(R.string.location_name_storage), "")
    }

    fun getUseCelsius(): Boolean {
        return App.sharedPreferences.getBoolean(grabString(R.string.use_celsius_storage), false)
    }

    @SuppressLint("ApplySharedPref")
    fun getStoredShouldUseCurLocation(): Boolean {
        val returnVal = App.sharedPreferences.getBoolean(grabString(R.string.location_preference_storage), true)
        if (!App.sharedPreferences.contains(grabString(R.string.location_preference_storage))) {
            //default should be true and use commit to be synchronous
            App.sharedPreferences.edit().putBoolean(grabString(R.string.location_preference_storage), true).commit()
        }
        return returnVal
    }

    fun storeShouldUseCurLocation(shouldUserCurLocation: Boolean) {
        App.sharedPreferences.edit().putBoolean(grabString(R.string.location_preference_storage), shouldUserCurLocation).apply()
    }

    fun storeSavedLat(lat: String?) {
        App.sharedPreferences.edit().putString(grabString(R.string.lat_storage), lat).apply()
    }

    fun storeSavedLong(lon: String?) {
        App.sharedPreferences.edit().putString(grabString(R.string.lon_storage), lon).apply()
    }

    fun storeSavedZip(zip: String) {
        App.sharedPreferences.edit().putString(grabString(R.string.zip_storage), zip).apply()
    }

    fun storeUseCelsius(useCelsius : Boolean){
        App.sharedPreferences.edit().putBoolean(grabString(R.string.use_celsius_storage), useCelsius).apply()
    }

    fun storeSavedLocationName(locationName : String){
        App.sharedPreferences.edit().putString(grabString(R.string.location_name_storage), locationName).apply()
    }
}