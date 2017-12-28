package com.joeracosta.uglyweather.util

import org.greenrobot.eventbus.EventBus

/**
 * Created by Joe on 12/27/2017.
 */

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