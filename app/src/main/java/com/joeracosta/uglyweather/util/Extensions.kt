package com.joeracosta.uglyweather.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Joe on 12/26/2017.
 */

fun <T : Any> Observable<T>.offMain() : Observable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun Float.convertToFahrenheit() : Int {
    return convertToFahrenheit(this)
}