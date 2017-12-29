package com.joeracosta.uglyweather.view

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import com.joeracosta.library.activity.FragmentStackActivity
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.util.Data
import com.joeracosta.uglyweather.util.SessionData
import com.joeracosta.uglyweather.util.StoredData
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider


class SingleActivity : FragmentStackActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stack_activity)

        if (!hasFragments()) {
            if (StoredData.getStoredShouldUseCurLocation()) {
                loadCurrentLocation()
            } else{
                SessionData.updateLocation(StoredData.getStoredLat(), StoredData.getStoredLon())
            }
            addFragmentToStack(MapFragment(), R.id.main_view, null, null)
        }
    }

    private fun loadCurrentLocation() {
        checkLocationPermission()
                .subscribe({ granted ->
                    if (granted) {
                        loadLastKnownLocation()
                    } else {
                        Data.useSavedLocation()
                    }
                }).addToComposite()
    }

    fun checkLocationPermission() : Observable<Boolean> {
        return RxPermissions(this).request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    @SuppressLint("MissingPermission")
    fun loadLastKnownLocation(){
        ReactiveLocationProvider(this).lastKnownLocation //todo add case for location failure??
                .subscribe({ location ->
                    SessionData.updateLocation(location.latitude.toString(), location.longitude.toString())
                }).addToComposite()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun Disposable.addToComposite() {
        disposables.add(this)
    }
}
