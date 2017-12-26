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
@BindingAdapter(value = ["swipecolor1", "swipecolor2", "swipecolor3"], requireAll = true)
fun setSwipeRefreshColors(view : SwipeRefreshLayout, colorRes1 : Int, colorRes2: Int, colorRes3: Int){
    view.setColorSchemeColors(grabColor(colorRes1), grabColor(colorRes2), grabColor(colorRes3))

}