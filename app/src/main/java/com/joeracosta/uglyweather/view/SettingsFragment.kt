package com.joeracosta.uglyweather.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.SimpleFragment
import com.joeracosta.uglyweather.databinding.SettingsFragmentBinding
import com.joeracosta.uglyweather.viewmodel.SettingsFragmentViewModel

/**
 * Created by Joe on 12/28/2017.
 */
class SettingsFragment : SimpleFragment() {

    private lateinit var viewModel : SettingsFragmentViewModel
    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsFragmentViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.saveZipCodeLocation()
    }

}