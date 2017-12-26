package com.joeracosta.uglyweather.view

import android.os.Bundle
import com.joeracosta.library.activity.FragmentStackActivity
import com.joeracosta.uglyweather.R

class MainActivity : FragmentStackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!hasFragments()){
            addFragmentToStack(FragmentMap(), R.id.main_view, null, null)
        }
    }
}
