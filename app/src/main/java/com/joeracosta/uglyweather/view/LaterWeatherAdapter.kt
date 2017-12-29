package com.joeracosta.uglyweather.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.joeracosta.uglyweather.databinding.LaterWeatherCardBinding
import com.joeracosta.uglyweather.model.LaterWeatherDay
import com.joeracosta.uglyweather.util.UseCelsiusEvent
import com.joeracosta.uglyweather.viewmodel.LaterWeatherCardViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Joe on 12/28/2017.
 */
class LaterWeatherAdapter(var laterWeatherData: List<LaterWeatherDay>) : RecyclerView.Adapter<LaterWeatherCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaterWeatherCardViewHolder {
        return LaterWeatherCardViewHolder(LaterWeatherCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LaterWeatherCardViewHolder?, position: Int) {
        holder?.bind(laterWeatherData[position])
    }

    override fun getItemCount(): Int {
        return laterWeatherData.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        EventBus.getDefault().register(this)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onCelsiusPreferenceSwitched(event : UseCelsiusEvent){
        notifyDataSetChanged()
    }
}

class LaterWeatherCardViewHolder(val binding: LaterWeatherCardBinding) : RecyclerView.ViewHolder(binding.root) {

    val viewModel = LaterWeatherCardViewModel()

    init {
        binding.viewModel = viewModel
    }

    fun bind(laterWeatherDay: LaterWeatherDay) {
        viewModel.laterWeatherDay = laterWeatherDay
        binding.executePendingBindings()
    }


}