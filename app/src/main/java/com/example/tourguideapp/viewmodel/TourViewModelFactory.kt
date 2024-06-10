package com.example.tourguideapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourguideapp.data.retrofit.ApiService

class TourViewModelFactory(private val service: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TourViewModel::class.java)) {
            return TourViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}