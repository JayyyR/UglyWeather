package com.joeracosta.uglyweather.view

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.SimpleFragment
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.viewmodel.NowFragmentViewModel

/**
 * Created by Joe on 12/25/2017.
 */
class NowFragment : SimpleFragment() {

    private lateinit var viewModel : NowFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.now_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NowFragmentViewModel::class.java)
        viewModel.observeWeather()
    }
}