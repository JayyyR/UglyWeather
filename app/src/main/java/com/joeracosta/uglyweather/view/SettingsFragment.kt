package com.joeracosta.uglyweather.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.joeracosta.library.activity.SimpleFragment
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.databinding.SettingsFragmentBinding
import com.joeracosta.uglyweather.util.Data
import com.joeracosta.uglyweather.util.StoredData
import com.joeracosta.uglyweather.viewmodel.SettingsFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Joe on 12/28/2017.
 */
class SettingsFragment : SimpleFragment() {

    private val disposables = CompositeDisposable()
    private lateinit var viewModel: SettingsFragmentViewModel
    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsFragmentViewModel::class.java)

        viewModel.alertUserSubject.subscribe({ stringRes ->
            Snackbar.make(binding.root, stringRes, Snackbar.LENGTH_SHORT).show()
        }).addToComposite()

        binding.view = this
        binding.viewModel = viewModel
    }

    //listener in the view because we need the activity reference to check for permissions and update location
    var curLocCheckListener = CompoundButton.OnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            (activity as SingleActivity).checkLocationPermission().subscribe({ granted ->
                viewModel.useCurLocation = granted
                StoredData.storeShouldUseCurLocation(granted)
                if (granted) {
                    (activity as SingleActivity).loadLastKnownLocation()
                } else {
                    Snackbar.make(binding.root, R.string.enable_location_permission, Snackbar.LENGTH_SHORT).show()
                }
            }).addToComposite()
        } else {
            viewModel.useCurLocation = false
            Data.useSavedLocation()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun Disposable.addToComposite() {
        disposables.add(this)
    }
}