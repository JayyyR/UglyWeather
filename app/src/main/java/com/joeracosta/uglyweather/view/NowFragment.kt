package com.joeracosta.uglyweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.SimpleFragment
import com.joeracosta.uglyweather.R

/**
 * Created by Joe on 12/25/2017.
 */
class NowFragment : SimpleFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_now, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}