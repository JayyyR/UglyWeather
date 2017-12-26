package com.joeracosta.uglyweather.util

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView

/**
 * Created by Joe on 12/25/2017.
 */

@BindingAdapter("bottomNavListener")
fun setBottomNavListener(view : BottomNavigationView, listener : BottomNavigationView.OnNavigationItemSelectedListener){
    view.setOnNavigationItemSelectedListener(listener)
}