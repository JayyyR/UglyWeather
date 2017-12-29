package com.joeracosta.uglyweather.util
import android.annotation.SuppressLint
import com.joeracosta.uglyweather.App
import com.joeracosta.uglyweather.R

/**
 * Created by Joe on 12/27/2017.
 */

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