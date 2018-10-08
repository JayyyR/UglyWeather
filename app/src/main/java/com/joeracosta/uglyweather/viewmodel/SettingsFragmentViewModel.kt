package com.joeracosta.uglyweather.viewmodel

import android.databinding.Bindable
import android.view.View
import android.widget.CompoundButton
import com.crashlytics.android.Crashlytics
import com.joeracosta.uglyweather.BR
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.SmartViewModel
import com.joeracosta.uglyweather.network.geoAPI
import com.joeracosta.uglyweather.util.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by Joe on 12/28/2017.
 */
enum class LatestLoadingStatus{
    SUCCESS, FAILURE, LOADING, NONE
}
class SettingsFragmentViewModel : SmartViewModel() {

    private var latestLoadingStatus = LatestLoadingStatus.NONE
    set (value){
        field = value
        notifyChange()
    }

    @Bindable
    var useCurLocation = StoredData.getStoredShouldUseCurLocation()
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var useCelsius = StoredData.getDegreeType()

    @Bindable
    var zipCode = StoredData.getStoredZip()

    @Bindable
    var savedLocationText = StoredData.getStoredLocationName()
        set (value){
            field = value
            notifyPropertyChanged(BR.zipLoadStatusVisibility)
            notifyPropertyChanged(BR.zipLoadStatusResource)
            notifyPropertyChanged(BR.zipLoadLoadingVisibility)
        }

    @Bindable
    fun getZipLoadStatusResource() : Int {
       return when (latestLoadingStatus) {
            LatestLoadingStatus.NONE -> 0
            LatestLoadingStatus.FAILURE -> R.drawable.ic_error
            LatestLoadingStatus.SUCCESS -> R.drawable.ic_success_check
            LatestLoadingStatus.LOADING -> 0
        }
    }

    @Bindable
    fun getZipLoadStatusVisibility() : Int {
       return when (latestLoadingStatus) {
            LatestLoadingStatus.NONE -> View.GONE
            LatestLoadingStatus.FAILURE -> View.VISIBLE
            LatestLoadingStatus.SUCCESS -> View.VISIBLE
            LatestLoadingStatus.LOADING -> View.GONE
        }
    }

    @Bindable
    fun getZipLoadLoadingVisibility() : Int {
        return when (latestLoadingStatus) {
            LatestLoadingStatus.NONE -> View.GONE
            LatestLoadingStatus.FAILURE -> View.GONE
            LatestLoadingStatus.SUCCESS -> View.GONE
            LatestLoadingStatus.LOADING -> View.VISIBLE
        }
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
        StoredData.storeDegreeType(isChecked)
        useCelsius = isChecked
    }


    fun saveZip() {
        if (zipCode.isEmpty()){
            return
        }
        latestLoadingStatus = LatestLoadingStatus.LOADING
        geoAPI.getLatLong(grabString(R.string.geocoding_api_key), zipCode)
                .offMain()
                .subscribe({ geoResponse ->
                    if (geoResponse.status != "OK") {
                        alertUserSubject.onNext(R.string.valid_zip)
                        latestLoadingStatus = LatestLoadingStatus.FAILURE
                    } else {
                        latestLoadingStatus = LatestLoadingStatus.SUCCESS
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

                }, { error ->
                    Crashlytics.logException(error)
                    latestLoadingStatus = LatestLoadingStatus.FAILURE
                    alertUserSubject.onNext(R.string.error_server)
                }).addToComposite()
    }
}