package com.example.tourguideapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tourguideapp.data.model.Photo
import com.example.tourguideapp.databinding.ItemTourBinding

class PhotoViewHolder(private val binding: ItemTourBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) = with(binding) {
        tvDesc.text = photo.title

        // Load photo using Glide
        Glide.with(itemView.context)
            .load(photo.url)
            .into(ivImage)
    }

    companion object {
        fun create(parent: ViewGroup): PhotoViewHolder {
            return PhotoViewHolder(
                ItemTourBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}