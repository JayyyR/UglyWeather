package com.joeracosta.uglyweather.view

import android.os.Bundle
import com.joeracosta.library.activity.FragmentStackActivity
import com.joeracosta.uglyweather.R

class StackActivity : FragmentStackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stack_activity)

        if (!hasFragments()){
            addFragmentToStack(MapFragment(), R.id.main_view, null, null)
        }
    }
}
