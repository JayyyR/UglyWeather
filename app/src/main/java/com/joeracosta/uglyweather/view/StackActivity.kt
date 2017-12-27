package com.joeracosta.uglyweather.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.joeracosta.library.activity.FragmentStackActivity
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.lastLat
import com.joeracosta.uglyweather.lastLon
import com.joeracosta.uglyweather.util.grabString
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider


class StackActivity : FragmentStackActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stack_activity)

        if (!hasFragments()) {
            initializeWithLocation()
        }
    }

    private fun initializeWithLocation() {
        RxPermissions(this).request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({ granted ->
                    if (granted) {
                        loadNewLocation()
                    } else {
                        loadOldLocation()
                        showInitialUI()
                    }
                }).addToComposite()
    }

    @SuppressLint("MissingPermission")
    private fun loadNewLocation() {
        ReactiveLocationProvider(this).lastKnownLocation
                .subscribe({ location ->
                    lastLon = location.longitude.toString()
                    lastLat = location.latitude.toString()
                    saveLastLocation(lastLat as String, lastLon as String)
                }, {
                    //todo fail getting location, prompt user to add zip
                    loadOldLocation()
                }, {
                    showInitialUI()
                }).addToComposite()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun Disposable.addToComposite() {
        disposables.add(this)
    }

    private fun saveLastLocation(lat: String, lon: String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(grabString(R.string.lat_storage), lat)
        editor.putString(grabString(R.string.lon_storage), lon)
        editor.apply()
    }

    private fun loadOldLocation(){
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        lastLat = sharedPref.getString(grabString(R.string.lat_storage), null)
        lastLon = sharedPref.getString(grabString(R.string.lon_storage), null)
    }

    private fun showInitialUI(){
        addFragmentToStack(MapFragment(), R.id.main_view, null, null)
    }
}
