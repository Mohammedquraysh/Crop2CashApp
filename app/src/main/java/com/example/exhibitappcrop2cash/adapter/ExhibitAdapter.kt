package com.example.exhibitappcrop2cash.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.exhibitappcrop2cash.databinding.ExhibitLayoutBindingBinding
import com.example.exhibitappcrop2cash.model.ExhibitModelResponseItem

class ExhibitAdapter: RecyclerView.Adapter<ExhibitAdapter.ExhibitViewHolder>() {

    var diffUtilCallBack = object : DiffUtil.ItemCallback<ExhibitModelResponseItem>() {

        override fun areItemsTheSame(oldItem: ExhibitModelResponseItem, newItem: ExhibitModelResponseItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ExhibitModelResponseItem, newItem: ExhibitModelResponseItem): Boolean {
            return oldItem.title == newItem.title && oldItem.images == newItem.images
        }
    }

    var differ = AsyncListDiffer(this, diffUtilCallBack)

    inner class ExhibitViewHolder(binding: ExhibitLayoutBindingBinding):RecyclerView.ViewHolder(binding.root){

        private val exhibitTitle = binding.tvExhibitTitle
        private val childRecyclerView = binding.childRecyclerview


        fun bindView(exhibit: ExhibitModelResponseItem){
            Log.d("MQ", "${exhibit.title}this is the first adapter")
            exhibitTitle.text = exhibit.title

            childRecyclerView.apply {
                Log.d("MQ", "${exhibit.title}this is the first adapter three")
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
                addItemDecoration(decoration)
                val childRecyclerViewAdapter = ExhibitChildAdapter(exhibit.images as MutableList<String>)
                adapter = childRecyclerViewAdapter

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExhibitAdapter.ExhibitViewHolder {
        val binding = ExhibitLayoutBindingBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ExhibitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExhibitAdapter.ExhibitViewHolder, position: Int) {
        holder.bindView(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}