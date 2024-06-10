package com.example.tourguideapp.view

import androidx.recyclerview.widget.DiffUtil
import com.example.tourguideapp.data.model.Photo

class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}