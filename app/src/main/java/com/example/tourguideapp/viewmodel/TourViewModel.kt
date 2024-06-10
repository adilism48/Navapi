package com.example.tourguideapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tourguideapp.data.retrofit.ApiService
import kotlinx.coroutines.Dispatchers

class TourViewModel(private val apiService: ApiService) : ViewModel() {
    val photo = liveData(Dispatchers.IO) {
        val retrievedTours = apiService.getPhotos()
        emit(retrievedTours)
    }
}