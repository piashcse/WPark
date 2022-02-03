package com.piashcse.wpark.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piashcse.wpark.data.model.FoodItem
import com.piashcse.wpark.databinding.FoodItemLayoutBinding

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private val items: MutableList<FoodItem> = arrayListOf()
    var onItemClick: ((FoodItem) -> Unit)? = null

    fun addItems(newItems: List<FoodItem>?, clearPreviousItem: Boolean = false) {
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

    inner class FoodViewHolder(private val binding: FoodItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FoodItem) {
            binding.foodItem = item
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val bind = FoodItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(bind)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = items[position]
        return holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}