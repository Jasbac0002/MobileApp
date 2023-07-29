package com.example.whatisthisapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RSSAdapter(
    private var items: List<RSSItem>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<RSSAdapter.RSSViewHolder>() {

    fun updateData(newItems: List<RSSItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rss_item_layout, parent, false)
        return RSSViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RSSViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { onItemClick(currentItem.link) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class RSSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val pubDateTextView: TextView = itemView.findViewById(R.id.pubDateTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(item: RSSItem) {
            titleTextView.text = item.title
            pubDateTextView.text = item.pubDate

            // Check if the imageUrl is not empty before loading the image
            if (item.imageUrl.isNotEmpty()) {
                Glide.with(itemView)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.loading_placeholder) // Placeholder image while loading
                    .error(R.drawable.error_placeholder) // Placeholder image when loading fails
                    .into(imageView)
            } else {
                // If imageUrl is empty, clear the ImageView to show nothing
                imageView.setImageDrawable(null)
            }
        }
    }
}
