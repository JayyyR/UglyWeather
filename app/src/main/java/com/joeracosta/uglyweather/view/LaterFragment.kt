package com.joeracosta.uglyweather.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.SimpleFragment
import com.joeracosta.uglyweather.databinding.LaterFragmentBinding
import com.joeracosta.uglyweather.model.LaterWeather
import com.joeracosta.uglyweather.viewmodel.LaterFragmentViewModel

/**
 * Created by Joe on 12/27/2017.
 */
class LaterFragment : SimpleFragment() {


    private lateinit var viewModel : LaterFragmentViewModel
    private lateinit var binding: LaterFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LaterFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LaterFragmentViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.observeWeather().observe(this, Observer<LaterWeather> {
            binding.swipeContainer.isRefreshing = false

            //todo recyerview
        })
    }
}