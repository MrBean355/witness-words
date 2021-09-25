package com.github.mrbean355.witnesswords

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mrbean355.witnesswords.core.LETTER_COUNT
import com.github.mrbean355.witnesswords.databinding.ItemWordBinding

class WordAdapter(
    private val onWordClicked: (String) -> Unit
) : ListAdapter<String, WordAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemWordBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = getItem(position)

        holder.binding.word.text = word
        holder.binding.word.typeface = if (word.length == LETTER_COUNT) Typeface.DEFAULT_BOLD else Typeface.DEFAULT

        holder.itemView.setOnClickListener {
            onWordClicked(getItem(holder.adapterPosition))
        }
    }

    class ViewHolder(val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return true
        }
    }
}