package com.github.mrbean355.witnesswords

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_word.view.word

class WordAdapter(
    private val onWordClicked: (String) -> Unit
) : ListAdapter<String, WordAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_word, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.word.text = getItem(position)
        holder.itemView.setOnClickListener {
            onWordClicked(getItem(holder.adapterPosition))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val word: TextView = itemView.word
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return true
        }
    }
}