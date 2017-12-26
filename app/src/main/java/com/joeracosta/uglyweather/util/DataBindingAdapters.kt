package com.joeracosta.uglyweather.util

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.SwipeRefreshLayout

/**
 * Created by Joe on 12/25/2017.
 */

@BindingAdapter("bottomNavListener")
fun setBottomNavListener(view : BottomNavigationView, listener : BottomNavigationView.OnNavigationItemSelectedListener){
    view.setOnNavigationItemSelectedListener(listener)
}

@BindingAdapter("swipeRefreshListener")
fun setSwipeRefreshListener(view : SwipeRefreshLayout, refreshListener : () -> Unit){
    view.setOnRefreshListener(refreshListener)
}