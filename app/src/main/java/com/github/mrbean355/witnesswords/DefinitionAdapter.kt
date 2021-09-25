package com.github.mrbean355.witnesswords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mrbean355.witnesswords.databinding.ItemDefinitionBinding

class Definition(
    val type: String,
    val detail: String
)

class DefinitionAdapter : ListAdapter<Definition, DefinitionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemDefinitionBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.definitionType.text = item.type.uppercase()
        holder.binding.definitionDetail.text = item.detail
    }

    class ViewHolder(val binding: ItemDefinitionBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Definition>() {

        override fun areItemsTheSame(oldItem: Definition, newItem: Definition): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Definition, newItem: Definition): Boolean {
            return true
        }
    }
}