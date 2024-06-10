package com.example.tourguideapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourguideapp.R
import com.example.tourguideapp.data.retrofit.ApiService
import com.example.tourguideapp.databinding.FragmentTourBinding
import com.example.tourguideapp.viewmodel.TourViewModel
import com.example.tourguideapp.viewmodel.TourViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class TourFragment : Fragment() {

    private var _binding: FragmentTourBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: PhotoAdapter

    override fun onStart() {
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_view).isVisible = true
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTourBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tourViewModel.photo.observe(viewLifecycleOwner) { photos ->
            adapter.submitList(photos)
        }

        binding.bLogOut.setOnClickListener {
            logout()
        }

        initRcView()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun logout() {
        val auth = Firebase.auth
        auth.signOut()
        this.findNavController().navigate(R.id.action_tourFragment_to_loginFragment)
    }

    private val tourViewModel: TourViewModel by viewModels {
        TourViewModelFactory(ApiService.create())
    }

    private fun initRcView() = with(binding) {
        adapter = PhotoAdapter()
        rcvTours.layoutManager = LinearLayoutManager(context)
        rcvTours.adapter = adapter
    }
}