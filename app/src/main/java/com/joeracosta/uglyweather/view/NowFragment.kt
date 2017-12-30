package com.joeracosta.uglyweather.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.SimpleFragment
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.databinding.NowFragmentBinding
import com.joeracosta.uglyweather.model.NowWeather
import com.joeracosta.uglyweather.viewmodel.NowFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Joe on 12/25/2017.
 */
class NowFragment : SimpleFragment() {

    private lateinit var viewModel : NowFragmentViewModel
    private lateinit var binding: NowFragmentBinding
    private var disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NowFragmentViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.alertUserSubject.subscribe({ stringRes ->
            if (stringRes == R.string.error_server){
                binding.swipeContainer.isRefreshing = false
            }
            Snackbar.make(binding.root, stringRes, Snackbar.LENGTH_SHORT).show()
        }).addToComposite()

        viewModel.observeWeather().observe(this, Observer<NowWeather> {
            binding.swipeContainer.isRefreshing = false
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun Disposable.addToComposite() {
        disposables.add(this)
    }
}