package com.xfinity.features.masterdetail.data.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xfinity.R
import com.xfinity.data.model.response.Article
import com.xfinity.data.model.response.RelatedTopic
import com.xfinity.databinding.ItemDetailBinding
import com.xfinity.features.masterdetail.CharacterViewModel

class ItemListAdapter : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {
    private var items: List<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_detail, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateCharacterList(items: List<Article>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        private val characterViewModel = CharacterViewModel()

        fun bind(character: Article) {
            characterViewModel.bind(character)
            binding.viewModel = characterViewModel
        }
    }
}