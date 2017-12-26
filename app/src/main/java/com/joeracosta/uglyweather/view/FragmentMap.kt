package com.joeracosta.uglyweather.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joeracosta.library.activity.FragmentMapFragment
import com.joeracosta.uglyweather.R
import com.joeracosta.uglyweather.databinding.MapLayoutBinding

/**
 * Created by Joe on 12/23/2017.
 */

class FragmentMap : FragmentMapFragment() {

    lateinit var binding : MapLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = MapLayoutBinding.inflate(inflater)
        binding.view = this
        return binding.root
    }

    fun getNavigationListener() : BottomNavigationView.OnNavigationItemSelectedListener {
        return bottomNavListener
    }

    private val bottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_now -> showFragmentInMap(NowFragment(), R.id.content_view, R.id.menu_now.toString())
            R.id.menu_later -> System.out.print("")
            R.id.menu_location -> System.out.print("")
        }
        true
    }

}