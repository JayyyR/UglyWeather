package com.joeracosta.uglyweather.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crashlytics.android.Crashlytics
import com.joeracosta.library.activity.SimpleFragment
import com.joeracosta.uglyweather.data.LaterWeather
import com.joeracosta.uglyweather.databinding.LaterFragmentBinding
import com.joeracosta.uglyweather.util.StoredData
import com.joeracosta.uglyweather.viewmodel.LaterFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Joe on 12/27/2017.
 */
class LaterFragment : SimpleFragment() {

    private lateinit var viewModel : LaterFragmentViewModel
    private lateinit var binding: LaterFragmentBinding
    private var adapter : LaterWeatherAdapter? = null
    private var disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StoredData.degreeTypeChanged.subscribe({
            adapter?.notifyDataSetChanged()
        }, {
            Crashlytics.logException(it)
        }).addToComposite()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LaterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LaterFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.recyclerViewLaterWeather.layoutManager = LinearLayoutManager(context)

        viewModel.alertUserSubject.subscribe { stringRes ->
            Snackbar.make(binding.root, stringRes, Snackbar.LENGTH_LONG).show()
        }.addToComposite()

        viewModel.observeWeather().observe(this, Observer<LaterWeather> { laterWeather ->
            laterWeather?.data?.let {
                if (adapter == null){
                    adapter = LaterWeatherAdapter(it)
                    binding.recyclerViewLaterWeather.adapter = adapter
                } else{
                    adapter?.laterWeatherData = it
                    adapter?.notifyDataSetChanged()
                }
            }
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