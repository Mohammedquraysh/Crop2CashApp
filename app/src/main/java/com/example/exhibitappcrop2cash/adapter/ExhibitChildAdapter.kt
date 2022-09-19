package com.example.exhibitappcrop2cash.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.exhibitappcrop2cash.R
import com.example.exhibitappcrop2cash.databinding.ChildLayoutAdapterBinding




 class ExhibitChildAdapter(memberData: MutableList<String>) : RecyclerView.Adapter<ExhibitChildAdapter.DataViewHolder>() {

     var membersList: MutableList<String> = mutableListOf()

    init {
        this.membersList = memberData
    }


    inner class DataViewHolder(binding: ChildLayoutAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
       var images = binding.ivExhibitImage

        fun bind(images: String) {

            val url = images
            Glide.with(itemView.context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().placeholder(R.drawable.black_iphone_2))
                .into(this.images)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :  ExhibitChildAdapter.DataViewHolder{
        val binding = ChildLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(membersList[position])

    }

    override fun getItemCount(): Int = membersList.size


}