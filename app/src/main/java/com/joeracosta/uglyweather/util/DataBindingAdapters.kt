package com.joeracosta.uglyweather.util

import android.databinding.BindingAdapter
import android.graphics.drawable.AnimationDrawable
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.*
import com.joeracosta.uglyweather.data.DegreeType
import com.joeracosta.uglyweather.data.degreeNameToDegreeType

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

@BindingAdapter("animationSrc")
fun setImageResource(view : ImageView, resource :Int){
    view.setImageResource(resource)

    if (view.drawable is AnimationDrawable){
        (view.drawable as AnimationDrawable).start()
    }
}

@BindingAdapter("toggleListener")
fun setToggleListener(view : Switch, listener : CompoundButton.OnCheckedChangeListener){
    view.setOnCheckedChangeListener(listener)
}

@BindingAdapter("spinnerListener")
fun spinnerListener(spinner: Spinner, degreeListener: (degreeType: DegreeType) -> Unit) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val selectedDegreeType = degreeNameToDegreeType(parent?.getItemAtPosition(position) as String)
            selectedDegreeType?.let(degreeListener::invoke)
        }

    }
}