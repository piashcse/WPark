package com.piashcse.wpark.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.piashcse.wpark.R
import com.piashcse.wpark.data.model.FoodItem
import com.piashcse.wpark.databinding.FoodItemLayoutBinding

/**
 * The Food adapter to show the city in a list.
 */
class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private val items: MutableList<FoodItem> = arrayListOf()
    var onItemClick: ((FoodItem, ImageView) -> Unit)? = null

    /**
     * Add items method for adding data and there is flag for clear data if necessary from user end
     */
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
            binding.apply {
                foodItem = item
                imageView.transitionName =
                    root.context.getString(
                        R.string.item_image_transition_name,
                        layoutPosition
                    )
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(item, binding.imageView)
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