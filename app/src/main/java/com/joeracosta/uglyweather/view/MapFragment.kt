package com.joeracosta.uglyweather.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.FragmentMapFragment
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.databinding.MapFragmentBinding

/**
 * Created by Joe on 12/23/2017.
 */

class MapFragment : FragmentMapFragment() {

    lateinit var binding : MapFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MapFragmentBinding.inflate(inflater)
        binding.view = this
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasFragments()) {
            showFragmentInMap(NowFragment(), R.id.content_view, R.id.menu_now.toString())
        }
    }

    val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_now -> showFragmentInMap(NowFragment(), R.id.content_view, R.id.menu_now.toString())
            R.id.menu_later -> showFragmentInMap(LaterFragment(), R.id.content_view, R.id.menu_later.toString())
            R.id.menu_settings -> showFragmentInMap(SettingsFragment(), R.id.content_view, R.id.menu_settings.toString())
        }
        true
    }

}