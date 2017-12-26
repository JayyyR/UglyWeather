package com.joeracosta.uglyweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.FragmentMapFragment
import com.joeracosta.uglyweather.R

/**
 * Created by Joe on 12/23/2017.
 */
class FragmentMap : FragmentMapFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.map_layout, container, false)
    }
}