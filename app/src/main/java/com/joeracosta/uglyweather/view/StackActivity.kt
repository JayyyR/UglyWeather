package com.joeracosta.uglyweather.view

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.joeracosta.library.activity.FragmentStackActivity
import com.joeracosta.uglyweather.R
import com.tbruyelle.rxpermissions2.Permission
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
            addFragmentToStack(MapFragment(), R.id.main_view, null, null)
            requestPermissions()
        }
    }

    private fun requestPermissions(){
        RxPermissions(this).requestEach(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({ permissions ->
                    if (permissions.granted){
                        getLocation()
                    } else{
                        //todo need to enable permissions
                    }
                }).addToComposite()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        ReactiveLocationProvider(this).lastKnownLocation
                .subscribe({ location ->
                    System.out.print("")
                }, {
                    System.out.print("") //todo fail getting location
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
