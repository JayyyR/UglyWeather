package com.joeracosta.uglyweather.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.SimpleFragment
import com.joeracosta.uglyweather.databinding.NowFragmentBinding
import com.joeracosta.uglyweather.viewmodel.NowFragmentViewModel

/**
 * Created by Joe on 12/25/2017.
 */
class NowFragment : SimpleFragment() {

    private lateinit var viewModel : NowFragmentViewModel
    private lateinit var binding: NowFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NowFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NowFragmentViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.observeWeather()
    }
}