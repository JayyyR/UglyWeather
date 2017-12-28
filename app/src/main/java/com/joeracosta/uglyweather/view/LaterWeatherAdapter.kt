package com.joeracosta.uglyweather.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.joeracosta.uglyweather.model.LaterWeatherDay

/**
 * Created by Joe on 12/28/2017.
 */
class LaterWeatherAdapter(var laterWeatherData: List<LaterWeatherDay>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return laterWeatherData.size
    }
}

class LaterWeatherCardViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

}