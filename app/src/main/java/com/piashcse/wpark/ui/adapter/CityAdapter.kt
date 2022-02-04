package com.piashcse.wpark.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.piashcse.wpark.R
import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.databinding.CityItemLayoutBinding

/**
 * The City adapter to show the city in a list.
 */
class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private val items: MutableList<CityItem> = arrayListOf()
    var onItemClick: ((CityItem, ImageView) -> Unit)? = null

    /**
     * Add items method for adding data and there is flag for clear data if necessary from user end
     */
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
            binding.apply {
                cityItem = item
                imageView.transitionName =
                    root.context.getString(R.string.item_image_transition_name, layoutPosition)
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(item, binding.imageView)
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