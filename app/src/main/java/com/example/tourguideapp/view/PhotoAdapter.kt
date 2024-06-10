package com.example.tourguideapp.view

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.tourguideapp.data.model.Photo

class PhotoAdapter : ListAdapter<Photo, PhotoViewHolder>(PhotoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}