package com.joeracosta.uglyweather.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.joeracosta.library.activity.FragmentStackActivity
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.util.grabString
import com.joeracosta.uglyweather.util.latitude
import com.joeracosta.uglyweather.util.longitude
import com.joeracosta.uglyweather.util.updateLocation
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider


class SingleActivity : FragmentStackActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stack_activity)

        if (!hasFragments()) {

            if (true) {//todo check if curloc setting on
                initializeWithLocation()
            } else{
                loadSavedLocation()
            }

            addFragmentToStack(MapFragment(), R.id.main_view, null, null)
        }
    }

    private fun initializeWithLocation() {
        RxPermissions(this).request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({ granted ->
                    if (granted) {
                        loadNewLocation()
                    } else {
                        //todo setcurloc to false
                        loadSavedLocation()
                    }
                }).addToComposite()
    }

    @SuppressLint("MissingPermission")
    private fun loadNewLocation() {
        ReactiveLocationProvider(this).lastKnownLocation
                .subscribe({ location ->
                    updateLocation(location.latitude.toString(), location.longitude.toString())
                }, {
                    //todo fail getting location, prompt user to add zip
                    //todo set cur loc to false
                    loadSavedLocation()
                }).addToComposite()
    }

    private fun storeSavedLocation(lat: String, lon: String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(grabString(R.string.lat_storage), lat)
        editor.putString(grabString(R.string.lon_storage), lon)
        editor.apply()
    }

    private fun loadSavedLocation(){
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        updateLocation(sharedPref.getString(grabString(R.string.lat_storage), null), sharedPref.getString(grabString(R.string.lon_storage), null))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun Disposable.addToComposite() {
        disposables.add(this)
    }
}
