package com.piashcse.wpark.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.databinding.CityItemLayoutBinding

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private val items: MutableList<CityItem> = arrayListOf()
    var onItemClick: ((CityItem) -> Unit)? = null

    fun addItems(newItems: List<CityItem>?, clearPreviousItem: Boolean = false) {
        newItems?.let {
            if (clearPreviousItem) {
                items.clear()
                items.addAll(newItems)
            } else {
                items.addAll(newItems)
            }
            notifyDataSetChanged()
        }
    }

    inner class CityViewHolder(private val binding: CityItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CityItem) {
            binding.cityItem = item
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val bind = CityItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(bind)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = items[position]
        return holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}