package com.emrexample.mobile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emrexample.mobile.R
import com.emrexample.mobile.databinding.ItemStarBinding
import com.emrexample.mobile.fragment.SelectionOfStarFragment
import com.emrexample.mobile.model.Star

class StarsRecyclerAdapter(var fragment: SelectionOfStarFragment) : RecyclerView.Adapter<StarsRecyclerAdapter.ViewHolder>() {

    private var data: List<Star> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val cardStarBinding: ItemStarBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.item_star, parent, false
        )
        return ViewHolder(cardStarBinding,fragment)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data.isNotEmpty()) {
            holder.bind(data[position])
        }
    }

    fun setData(starModel: List<Star>) {
        data = starModel
        this.notifyDataSetChanged()
    }

    class ViewHolder(var starBinding: ItemStarBinding,var fragment1: SelectionOfStarFragment) :
        RecyclerView.ViewHolder(starBinding.root) {
        fun bind(star: Star) {
            starBinding.star = star
            starBinding.fragment = fragment1
        }
    }
}