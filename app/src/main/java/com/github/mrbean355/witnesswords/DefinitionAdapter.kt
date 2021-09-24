package com.github.mrbean355.witnesswords

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_definition.view.definition_detail
import kotlinx.android.synthetic.main.item_definition.view.definition_type

class Definition(
        val type: String,
        val detail: String
)

class DefinitionAdapter : ListAdapter<Definition, DefinitionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_definition, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.type.text = item.type.uppercase()
        holder.detail.text = item.detail
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val type: TextView = itemView.definition_type
        val detail: TextView = itemView.definition_detail
    }

    class DiffCallback : DiffUtil.ItemCallback<Definition>() {

        override fun areItemsTheSame(oldItem: Definition, newItem: Definition): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Definition, newItem: Definition): Boolean {
            return true
        }
    }
}